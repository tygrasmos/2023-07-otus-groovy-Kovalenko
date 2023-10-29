import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

class ParseFile {

    String filePath
    Object result

    ParseFile(String filePath){
        this.filePath = filePath
    }

    Map<String, Object> parseFile(){
        List<String> list = getStringListFromFile(this.filePath)
        list = list.stream()
                .filter({ s -> (s != '') })
                .collect(Collectors.toList())

       /* for (def i = 0; i <= list.size(); i++){
            if (isSimpleObject(list.get(i))) {
                createMap(list.get(i), map)
            } else {
                List innerList = new ArrayList()
                innerList.add(list.get(i))
            }
        }
        list.each {
            if (isSimpleObject(it)) {
                createMap(it, map)
            } else {

            }
        }*/
        getMap(list)
    }

    private static Map<String, Object> getMap(List<String> list){
        Map<String, Object> map = new HashMap<>()
        for (def i = 0; i <= list.size(); i++){
            if (isSimpleObject(list.get(i))) {
                createMap(list.get(i), map)
            } else {
                List innerList = new ArrayList()
                innerList.add(list.get(i))
                getMap(innerList)
            }
        }
       /* list.each {
            if (isSimpleObject(it)) {
                createMap(it, map)
            } else {

            }
        }*/
    }

    private List<String> getStringListFromFile(String path) {
        List<String> stringList = new ArrayList<>()
        InputStream inputStream = getInputStream(path)

        if (inputStream != null) {
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            BufferedReader reader = new BufferedReader(streamReader)

            try {
                String s
                while ((s = reader.readLine()) != null) {
                    stringList.add(s)
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        stringList
    }

    private InputStream getInputStream(String path){
        try {
            ClassLoader classLoader = getClass().getClassLoader()
            return classLoader.getResourceAsStream(path)
        } catch(Exception e){
            throw new RuntimeException("Ошибка чтения файла", e.getCause())
        }
    }

    /** Проверим что объект в строке не является объектом */
    private static Boolean isSimpleObject(String string){
        if (string.contains("{") || string.contains("}") || string.contains("[") || string.contains("]")) {
            false
        } else {
            true
        }
    }

    private static Map createMap(String string, Map map){
        List strings = string.split("=").toList()
        if (strings.size() == 1) {
            strings = string.split(" ").toList()
            strings = strings.stream()
                    .map({ s -> s.trim() })
                    .map({s -> s.replace("\"", "")})
                    .collect(Collectors.toList())
            if (strings.size() == 1) {
                map.put(strings[0], null)
            } else {
                map.put(strings[0], strings[1])
            }
        } else {
            strings = strings.stream()
                    .map({ s -> s.trim() })
                    .map({s -> s.replace("\"", "")})
                    .collect(Collectors.toList())
            //Написать метод проверки каждого элемента на пустое значение
            if (string.contains("")) {
                ///????
            }
            map.put(strings[0], strings[1])
        }
        map
    }















}
