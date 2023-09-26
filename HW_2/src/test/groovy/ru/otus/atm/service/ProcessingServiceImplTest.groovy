package ru.otus.atm.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.otus.atm.entity.Banknote
import ru.otus.atm.entity.CashBox
import ru.otus.atm.entity.Currency
import ru.otus.atm.entity.Denomination
import ru.otus.atm.service.impl.ProcessingServiceImpl


class ProcessingServiceImplTest {

    @Autowired
    private ProcessingServiceImpl processingServiceImpl

    @Test
    void divisionWithoutRemainderMustBeCorrect(){
        assert ProcessingServiceImpl.getQuantity(125L, 100) == 1
    }

    @Test
    void amountInBanknotesMustBeCorrect(){
        List<Banknote> banknotes = new ArrayList<>()
        banknotes.add(getBanknote(100))
        banknotes.add(getBanknote(100))
        banknotes.add(getBanknote(100))
        banknotes.add(getBanknote(100))
        assert ProcessingServiceImpl.getAmountByBanknotes(banknotes) == 400L
    }

    @Test
    void amountInBanknotesMustBeEqualsAmount(){
        List<Banknote> banknotes = new ArrayList<>()
        banknotes.add(getBanknote(100))
        banknotes.add(getBanknote(100))
        banknotes.add(getBanknote(100))
        banknotes.add(getBanknote(100))
        assert ProcessingServiceImpl.isAmountEqualBanknoteAmount(banknotes, 400)
    }

    @Test
    void methodPlusMustBeCorrect(){
        assert ProcessingServiceImpl.plusCashBox(getCashBox(20, 100), 1).getQuantity() == getCashBox(21, 100).getQuantity()
    }

    @Test
    void methodMinusMustBeCorrect(){
        assert ProcessingServiceImpl.minusCashBox(getCashBox(20, 100), 1).getQuantity() ==
                getCashBox(19, 100).getQuantity()
    }

    @Test
    void quantityBanknotesToIssuedMustBeCorrect(){
        List<Banknote> banknotes = new ArrayList<Banknote>()
        ProcessingServiceImpl.addBanknotesFromReceive(banknotes, getCashBox(20, 100), 3)
        assert banknotes.size() == 3
    }

    @Test
    void calculationQuantityBanknotesToIssuedMustBeCorrect(){
        ProcessingServiceImpl.receiveAmountInBanknotes(1250, getCashBoxes()) == getBanknotes()
    }

    @Test
    void isThereRequiredAmountMustBeCorrect(){
        ProcessingServiceImpl.isThereRequiredAmount(1250, getCashBoxes())
    }

    private static List<CashBox> getCashBoxes(){
        List<CashBox> cashBoxes = new ArrayList<>()
        cashBoxes.add(getCashBox(20, 100))
        cashBoxes.add(getCashBox(20, 50))
        cashBoxes.add(getCashBox(20, 20))
        cashBoxes.add(getCashBox(20, 10))
        cashBoxes.add(getCashBox(20, 5))
        cashBoxes
    }

    private static List<Banknote> getBanknotes(){
        List<Banknote> banknotes = List.of(getBanknote(100), getBanknote(100), getBanknote(100), getBanknote(100), getBanknote(100),
                getBanknote(100), getBanknote(100), getBanknote(100), getBanknote(100), getBanknote(100),
                getBanknote(100), getBanknote(50))
        banknotes
    }

    private static Currency getCurrency(String currencyIdent){
        new Currency(1L, 'Российский рубль', currencyIdent )
    }

    private static Denomination getDenomination(Integer denomination){
        new Denomination(1L, denomination)
    }

    private static Banknote getBanknote(Integer denomination){
        new Banknote(1L, getCurrency('RUR'), getDenomination(denomination))
    }

    private static CashBox getCashBox(Integer quantity, Integer denomination){
        new CashBox(1L, getBanknote(denomination), quantity)
    }
}
