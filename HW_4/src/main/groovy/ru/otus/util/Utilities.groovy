package ru.otus.util

class Utilities {

    static LinkedHashMap trueSorted(Object o){
        LinkedHashMap oldMap = (LinkedHashMap) o.collect().collectEntries()
        LinkedHashMap newMap = new LinkedHashMap()
        while (newMap.size() < oldMap.size()) {
            oldMap.forEach({ k, v ->
                String keyName = k.toString()
                if (newMap.size() == 0) {
                    if (keyName == 'name') {
                        newMap.put(k, v)
                    }
                } else if (newMap.size() == 1) {
                    if (keyName == 'age') {
                        newMap.put(k, v)
                    }
                } else if (newMap.size() == 2) {
                    if (keyName == 'secretIdentity') {
                        newMap.put(k, v)
                    }
                } else if (newMap.size() >= 3) {
                    if (keyName == 'powers') {
                        newMap.put(k, v)
                    }
                } else {
                    // Тут будет код, если элементов для сортировки больше
                }
            })
        }
        newMap
    }

    static boolean isCollection(Object o){
        if (o.getClass() == ArrayList.class){
            true
        } else {
            false
        }
    }
}
