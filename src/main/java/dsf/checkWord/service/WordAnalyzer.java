package dsf.checkWord.service;

import dsf.checkWord.entity.*;
import dsf.checkWord.outlook.Showresult;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author 董少飞
 * @date 2018/10/6
 */
public interface WordAnalyzer {


    /**
     * 分析word文件
     */
    void analysis();

    static Map<Map<String, String>, Map<WordParagraph, Map<WordRun, String>>> check(AbstractWord wordMap, List<Map<String, String>> rules) {

        if(wordMap == null) {

            throw new IllegalArgumentException("wordMap不能为null");
        }

        Map<Map<String, String>, Map<WordParagraph, Map<WordRun, String>>> map = new LinkedHashMap<>();
//        HashMap<WordParagraph, Map<WordRun, String>> map = new LinkedHashMap<>();
        for(Map<String, String> rule : rules) {

            map.put(rule, checkByLevel(rule, wordMap));
        }

        String resultFileName;
        int index;
        File resultFile = null;
        String fileName = wordMap.getName();
        if((index = fileName.lastIndexOf('.')) >= 0) {

            String name = fileName.substring(0, index);
            String suffix = fileName.substring(index);
            resultFileName = name + "_result" + suffix;

            resultFile = new File(resultFileName);
            try {
                resultFile.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件失败");
                e.printStackTrace();
            }
        }
        wordMap.write(resultFile);
        return map;
    }

    static Map<WordParagraph, Map<WordRun, String>> checkByLevel(Map<String, String> rule, AbstractWord wordMap) {

        ParagraphRule paragraphRule = new ParagraphRule(rule);
        List<WordParagraph> paragraphs;
        HashMap<WordParagraph, Map<WordRun, String>> map = new LinkedHashMap<>();
        if((paragraphs = wordMap.getParagraphsByTitleLevel(paragraphRule.getLvl())) != null) {

            for(WordParagraph wordParagraph : paragraphs) {

                if(wordParagraph.isEmpty()) {
                    continue;
                }
                Map<WordRun, String> stringMap = paragraphRule.check(wordParagraph);
                if(stringMap != null && stringMap.size() != 0) {

                    map.put(wordParagraph, stringMap);
                }
            }
        }

        return map;
    }

    static Map<Map<String, String>, Map<WordParagraph, Map<WordRun, String>>> check(AbstractWord wordMap, Map<String, Map<String, String>> rules) {

        return check(wordMap, mapToList(rules));
    }

    static Map<String, Map<String, Map<String, String>>> getCheckResult(Map<Map<String, String>, Map<WordParagraph, Map<WordRun, String>>> map) {

        Map<String, Map<String, Map<String, String>>> result = new LinkedHashMap<>();
        for(Map.Entry<Map<String, String>, Map<WordParagraph, Map<WordRun, String>>> entry : map.entrySet()) {

            Map<String, String> key = entry.getKey();
            StringBuilder rule = new StringBuilder();
            rule.append("当前标题级别 ").append(key.get("标题级别"))
                .append("字体 ").append(key.get("字体"))
                .append("字号 ").append(key.get("字号"))
                .append("颜色 ").append(key.get("颜色"));
            result.put(rule.toString(), getCheckResultByParagraph(entry.getValue()));
        }

        return result;
    }

    static Map<String, Map<String, String>> getCheckResultByParagraph(Map<WordParagraph, Map<WordRun, String>> map) {

        Map<String, Map<String, String>> result = new LinkedHashMap<>();
        for(Map.Entry<WordParagraph, Map<WordRun, String>> entry : map.entrySet()) {

            Map<String, String> runResult = new LinkedHashMap<>();
            String lastKey = null, lastMes;
            Map<WordRun, String> value = entry.getValue();
            for(Map.Entry<WordRun, String> runEntry : value.entrySet()) {

                String mes = runEntry.getValue();
                String key;
                if((key = runEntry.getKey().text().trim()).equals("")) {

                    continue;
                }
                if(null != lastKey && null != (lastMes = runResult.get(lastKey))
                        && lastMes.equals(mes)) {

                    runResult.remove(lastKey);
                    lastKey = lastKey + key;
                    runResult.put(lastKey, lastMes);
                } else {

                    runResult.put(key, runEntry.getValue());
                    lastKey = key;
                }
            }
            result.put(entry.getKey().text(), runResult);
        }
        return result;
    }

//    static void showResults(Map<String, Map<String, Map<String, String>>> results) {
//
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        String resultFile = dateFormat.format(date) + "_result.txt";
//        File file = new File(resultFile);
//        System.out.println(file.getAbsolutePath());
//        try {
//            file.createNewFile();
//        } catch (IOException e1) {
//            System.out.println("create result file exception");
//            e1.printStackTrace();
//        }
//        try(PrintStream printStream = new PrintStream(file)) {
//            for(Map.Entry<String, Map<String, Map<String, String>>> word : results.entrySet()) {
//
//                printStream.println(word.getKey() + ":");
//                for(Map.Entry<String, Map<String, String>> paragraph : word.getValue().entrySet()) {
//
//                    printStream.println("   " + paragraph.getKey() + ":");
//                    for(Map.Entry<String, String> run : paragraph.getValue().entrySet()) {
//
//                        printStream.println("       " + run.getKey() + ":   " + run.getValue());
//                    }
//                }
//            }
//        } catch (FileNotFoundException e1) {
//            e1.printStackTrace();
//        }
//        try(FileInputStream fileInputStream = new FileInputStream(file)) {
//            byte[] bytes = new byte[Math.toIntExact(file.length())];
//            fileInputStream.read(bytes);
//            Showresult showresult = new Showresult(new String(bytes));
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//    }

    static void showResults(Map<String, Map<String, Map<String, Map<String, String>>>> results) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String resultFile = dateFormat.format(date) + "_result.txt";
        File file = new File(resultFile);
        System.out.println(file.getAbsolutePath());
        try {
            file.createNewFile();
        } catch (IOException e1) {
            System.out.println("create result file exception");
            e1.printStackTrace();
        }
        try(PrintStream printStream = new PrintStream(file)) {
            for(Map.Entry<String, Map<String, Map<String, Map<String, String>>>> word : results.entrySet()) {

                printStream.println(word.getKey() + ":");

                for(Map.Entry<String, Map<String, Map<String, String>>> titleLevel : word.getValue().entrySet()) {

                    printStream.println("   " + titleLevel.getKey());
                    for(Map.Entry<String, Map<String, String>> paragraph : titleLevel.getValue().entrySet()) {

                        printStream.println("       " + paragraph.getKey() + ":");
                        for(Map.Entry<String, String> run : paragraph.getValue().entrySet()) {

                            printStream.println("           " + run.getKey() + ":   " + run.getValue());
                        }
                    }
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[Math.toIntExact(file.length())];
            fileInputStream.read(bytes);
            Showresult showresult = new Showresult(new String(bytes));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    static List<Map<String, String>> mapToList(Map<String, Map<String, String>> map) {

        List<Map<String, String>> list = new LinkedList<>();
        for(Map.Entry<String, Map<String, String>> entry : map.entrySet()) {

            Map<String, String> node = new HashMap<>();
            node.put("标题级别", entry.getKey());
            node.putAll(entry.getValue());
            list.add(node);
        }

        return list;
    }


//    /**
//     * 更具规则检验wordMap是否合法
//     *
//     * @param wordMap
//     * @param rules
//     * @return
//     */
//    static HashMap<Integer, WordDifference> check(WordMap wordMap, Map<Integer, Map<String, String>> rules) {
//
//        HashMap<Integer, WordDifference> differenceMap = new HashMap<>();
//        Map<String, String> rule = null;
//        for (int key : rules.keySet()) {
//
//            if ((rule = rules.get(key)) != null) {
//
//                WordDifference wordDifference = new WordDifference(rule);
//                LinkedList<WordParagraph> paragraphs = wordMap.getParagraphsByTitleLevel(key);
//                for (WordParagraph wordParagraph : paragraphs) {
//
//                    if (!rule.equals(wordParagraph)) {
//
//                        wordDifference.addDifferenceParagraph(wordParagraph);
//                    }
//                }
//
//                differenceMap.put(key, wordDifference);
//            }
//        }
//        return differenceMap;
//    }


//    static HashMap<Integer, WordDifference> check(WordMap wordMap, Map<Integer, Map<String, String>> rules) {
//
//        Map<Integer, WordParagraph> paragraphMap = mapToParagraph(rules);
//        HashMap<Integer, WordDifference> differenceMap = new HashMap<>();
//        WordParagraph rule = null;
//        for (int key : rules.keySet()) {
//
//            if ((rule = paragraphMap.get(key)) != null) {
//
//                WordDifference wordDifference = new WordDifference(rule);
//                LinkedList<WordParagraph> paragraphs = wordMap.getParagraphsByTitleLevel(key);
//                for (WordParagraph wordParagraph : paragraphs) {
//
//                    if (!rule.equals(wordParagraph)) {
//
//                        wordDifference.addDifferenceParagraph(wordParagraph);
//                    }
//                }
//
//                differenceMap.put(key, wordDifference);
//            }
//        }
//        return differenceMap;
//    }

    static Map<Integer, WordParagraph> mapToParagraph(Map<Integer, Map<String, String>> map) {


        return null;
    }

//    static HashMap<Integer, WordDifference> check(WordMap wordMap, Map<Integer, Map<String, String>> rules) {
//
//    }

}
