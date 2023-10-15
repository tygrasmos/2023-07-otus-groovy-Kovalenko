package ru.otus.util

import org.junit.jupiter.api.Test

class UtilitiesTest {

    private final static List TEST_ARRAY = new ArrayList()

    @Test
    void testByCollectionMustBeCorrect(){
        assert Utilities.isCollection(TEST_ARRAY)
    }

    @Test
    void mapSortMustBeCorrect(){
        assert Utilities.trueSorted(getTestMap()) == getTrueMap()
    }

    static def getPowers(){
        List powers = new ArrayList()
        powers.add(100)
        powers.add(50)
        powers.add(70)
        powers
    }

    static def getTestMap(){
        Map map = new LinkedHashMap()
        map.put('age', 22)
        map.put('name', 'Пупкин Морква Свеклович')
        map.put('powers', getPowers())
        map.put('secretIdentity', '322-223')
        map
    }

    static def getTrueMap(){
        Map map = new LinkedHashMap()
        map.put('name', 'Пупкин Морква Свеклович')
        map.put('age', 22)
        map.put('secretIdentity', '322-223')
        map.put('powers', getPowers())
        map
    }

}
