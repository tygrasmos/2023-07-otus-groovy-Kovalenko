package ru.otus.atm.service.impl

import ru.otus.atm.entity.Banknote
import ru.otus.atm.entity.CashBox
import ru.otus.atm.exception.ProcessingException
import ru.otus.atm.service.repository.CashBoxService
import ru.otus.atm.service.repository.CurrencyService
import ru.otus.atm.service.ProcessingService

import java.util.stream.Collectors

class ProcessingServiceImpl implements ProcessingService{

    private final CashBoxService cashBoxService
    private final CurrencyService currencyService

    ProcessingServiceImpl(CashBoxService cashBoxService,
                          CurrencyService currencyService){
        this.cashBoxService = cashBoxService
        this.currencyService = currencyService
    }


    @Override
    List<Banknote> getBanknotes(Long amount, String currencyIdent) {
        try {
            List<CashBox> cashBoxesWithCurrentCurrency = getCashBoxes(currencyIdent)
            if(!isThereRequiredAmount(amount, cashBoxesWithCurrentCurrency)){
                throw new ProcessingException("Нехватате купюр для выдачи. Попробуйте ввести другую сумму.")
            }
            return receiveAmountInBanknotes(amount, cashBoxesWithCurrentCurrency)
        } catch (ProcessingException pe) {
            throw new ProcessingException(pe.getMessage(), pe.getCause())
        } catch (Exception e) {
            throw new ProcessingException(e.getMessage(), e.getCause())
        }

    }

    @Override
    Long addBanknotes(String currencyIdent, List<Banknote> banknotes) {
        try {
            List<CashBox> cashBoxesFromSave = new ArrayList<>()
            List<CashBox> cashBoxesWithCurrentCurrency = getCashBoxes(currencyIdent)
            Long amount = getAmountByBanknotes(banknotes)
            cashBoxesWithCurrentCurrency.forEach(
                    { cb ->
                        banknotes.forEach(
                                { b ->
                                    if (cb.getBanknote() == b) {
                                        //cashBoxesFromSave.add(plusCashBox(cb, 1))
                                        cashBoxesFromSave.add(cb + 1)
                                    }
                                })
                    })
            saveCashBoxes(cashBoxesFromSave)
            return amount
        } catch (Exception e){
            throw new ProcessingException("невозможно зачислить сумму на счет.".concat(e.getMessage()))
        }
    }

    /** Получим все ячейки с купюрами для данной валюты */
    List<CashBox> getCashBoxes(String currencyIdent){
        cashBoxService.findAll().stream()
                .filter(
                        {
                            it.getBanknote().getCurrency().getCurrencyIdent() == currencyIdent
                        })
                .sorted(new CashBoxComparator())
                .collect(Collectors.toList())
    }

    /** Проверим что заданная сумма в заданной валюте есть в банкомате */
    static boolean isThereRequiredAmount(Long amount, List<CashBox> cashBoxesWithCurrentCurrency){
        Long summ = 0L
        cashBoxesWithCurrentCurrency.forEach(
                {
                    summ += (Long) (it.getBanknote().getDenomination().getDenomination() * it.getQuantity())
                })
        summ > amount
    }

    /** Получим нужное количество банкнот для выдачи */
    static List<Banknote> receiveAmountInBanknotes(Long amount, List<CashBox> cashBoxes){
        List<Banknote> banknoteList = new ArrayList<>()
        List<CashBox> cashBoxesFromSave = new ArrayList<>()
        Long remains = amount
        cashBoxes.forEach(
                {
                    def requiredBanknoteQuantity = getQuantity(remains, it.getBanknote().getDenomination().getDenomination())
                    def banknoteQuantity = getBanknoteShortageQuantity(requiredBanknoteQuantity, it.getQuantity())
                    if(banknoteQuantity < 0){
                        addBanknotesFromReceive(banknoteList, it, requiredBanknoteQuantity + banknoteQuantity)
                        remains = remains - (requiredBanknoteQuantity + banknoteQuantity) * it.getBanknote().getDenomination().getDenomination()
                        //CashBox cashBoxFromSave = minusCashBox(it, (requiredBanknoteQuantity + banknoteQuantity))
                        CashBox cashBoxFromSave = it - (requiredBanknoteQuantity + banknoteQuantity)
                        cashBoxesFromSave.add(cashBoxFromSave)
                    } else {
                        addBanknotesFromReceive(banknoteList, it, requiredBanknoteQuantity)
                        remains = remains - (requiredBanknoteQuantity) * it.getBanknote().getDenomination().getDenomination()
                        //CashBox cashBoxFromSave = minusCashBox(it, requiredBanknoteQuantity)
                        CashBox cashBoxFromSave = it - requiredBanknoteQuantity
                        cashBoxesFromSave.add(cashBoxFromSave)
                    }
                })
        if(isAmountEqualBanknoteAmount(banknoteList, amount)){
         //   saveCashBoxes(cashBoxesFromSave)
            banknoteList
        } else {
            throw new ProcessingException("Невозможно выдать заданную сумму.")
        }
    }

    /** Проверим хватит ли купюр в банкомате для выдачи */
    static Integer getBanknoteShortageQuantity(Integer requiredBanknoteQuantity, Integer stockBanknoteQuantity){
        stockBanknoteQuantity - requiredBanknoteQuantity
    }

    /** Добавим нужное количество банктон данного номинала в список выдачи */
    static void addBanknotesFromReceive(List<Banknote> banknoteList, CashBox cashBox, Integer banknoteQuantity){
        for (int i; i < banknoteQuantity; i++) {
            banknoteList.add(cashBox.getBanknote())
        }
    }

    /** переопределим оператор - */
    static def minusCashBox(CashBox x1, Integer quantity){
        new CashBox(x1.getId(), x1.getBanknote(), x1.getQuantity() - quantity)
    }

    /** переопределим оператор + */
    static def plusCashBox(CashBox x1, Integer quantity){
        new CashBox(x1.getId(), x1.getBanknote(), x1.getQuantity() + quantity)
    }

    /** Проверим что необходимую сумму сожно выдать существующими купюрами */
    static boolean isAmountEqualBanknoteAmount(List<Banknote> banknoteList, Long amount){
        getAmountByBanknotes(banknoteList) == amount
    }

    /** Получим сумму для зачисления на счет */
    static Long getAmountByBanknotes(List<Banknote> banknoteList){
        Long summ = 0L
        banknoteList.forEach(
                {
                    summ += (Long) it.denomination.getDenomination()
                })
        summ
    }

    /** Получим максимальное количество данной купюры для выдачи */
    static Integer getQuantity(Long value, Integer nominal){
        value.intdiv(nominal)
    }

    /** Сохраним обновленную информацию о количестве купюр в банкомате */
    private void saveCashBoxes(List<CashBox> cashBoxes){
        cashBoxService.modifyOrAddAll(cashBoxes)
    }

    /** Компаратор для ранжирования ячеек с купюрами по номиналу */
    static class CashBoxComparator implements Comparator<CashBox> {
        @Override
        int compare(CashBox o1, CashBox o2) {
            return o1.getBanknote().getDenomination().getDenomination() <=>
                    o2.getBanknote().getDenomination().getDenomination()
        }
    }


}
