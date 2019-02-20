package dsf.checkWord.outlook;

import java.util.*;

public class Split
{
    //显示xml 的默认配置的所有详情
    public static List<String> mapToList(Map<String, Map<String, String>> map)
    {
        if(map==null)
            return  null;
        List<String> show=new LinkedList<>();
        Set<Map.Entry<String, Map<String, String>>> entries = map.entrySet();
        if(entries.isEmpty())
            return null;
        for(Map.Entry<String, Map<String, String>> a: entries )
        {
            String row= a.getKey();
            if(row.equals("0级"))
                row="正文";
            Map<String, String> property=a.getValue();
            Set<Map.Entry<String, String>> entries1 = property.entrySet();
            for(Map.Entry<String, String> b :entries1)
            {
                String s=b.getValue();
                row=row+","+s;
            }//拼接成一行显示信息
            show.add(row);
        }
        Collections.reverse(show);
        return show;
    }




    //显示所有xml的名字
    public static List<String> xmlname(Map<String, Map<String, Map<String, String>>> map)
    {
        if(map==null) {
            return null;
        }
        String s=null;
        List<String> show=new LinkedList<>();
        Set<Map.Entry<String, Map<String, Map<String, String>>>> entries = map.entrySet();
        for(Map.Entry<String, Map<String, Map<String, String>>> a:entries)
        {
            s=a.getKey();

            show.add(s);
        }
        return  show;
    }

    //用于获取xml中的详细信息
    public  static List<String> xmlshow(String s , Map<String, Map<String, Map<String, String>>> map)
    {
        if(s==null||map==null) {
            return null;
        }
        int count=0;
        List<String> show=new LinkedList<>();
        Map<String,Map<String,String>> select=map.get(s);
        Set<Map.Entry<String, Map<String, String>>> entries = select.entrySet();
        if(entries==null)
            return null;
        Map<String, String> property=new HashMap<>();

/*
            char t=10;
            for(Map.Entry<String, Map<String, String>> a :entries)//寻找最小级数
            {
                String row=a.getKey();
                char series=row.charAt(0);
                if(series<t)
                    t=series;
            }

            do {
                for(Map.Entry<String, Map<String, String>> a :entries)
                {
                    String row=a.getKey();
                    char series=row.charAt(0);
                    if(series==t)
                    {
                        if(series=='0') {
                            row = "正文";
                        }
                        property=a.getValue();
                        Set<Map.Entry<String, String>> entries1 = property.entrySet();
                        for(Map.Entry<String, String> b :entries1)
                        {
                            String f=b.getValue();
                            row=row+","+f;
                        }//拼接成一行显示信息
                        show.add(row);
                        count++;
                        t++;
                    }

                }

            }while (count<entries.size());
            */

        for(Map.Entry<String, Map<String, String>> a :entries)
        {
            String row= a.getKey();
            if(row.equals("0级"))
            {
                row="正文";
            }
            property=a.getValue();
            Set<Map.Entry<String, String>> entries1 = property.entrySet();
            for(Map.Entry<String, String> b :entries1)
            {
                String p=b.getValue();
                row=row+","+p;
            }//拼接成一行显示信息
            show.add(row);
        }

        Collections.reverse(show);
        return show;
    }
  /*
    //根据选择项的下标，获取被选中的xml的地址
    public  static  String name(int i,Map<String, Map<String, Map<String, String>>> map)
    {
        Set<Map.Entry<String, Map<String, Map<String, String>>>> entries = map.entrySet();
        if(entries==null)
            return null;
        List<String> address=new LinkedList<String>();
        for(Map.Entry<String, Map<String, Map<String, String>>> a:entries)
        {
            address.add(a.getKey());
        }
        return address.get(i);
    }

    //用于获取xml中的详细信息
    public  static List<String> xmlshow(int s , Map<String, Map<String, Map<String, String>>> map)
    {
        if(s==-1||map==null) {
            return null;
        }
        String name=Split.name(s,map);

        if(name==null)
            return  null;

        List<String> show=new LinkedList<>();
        Map<String,Map<String,String>> select=map.get(name);
        Set<Map.Entry<String, Map<String, String>>> entries = select.entrySet();
        for(Map.Entry<String, Map<String, String>> a :entries)
        {
            String row= a.getKey();
            if(row.equals("0级"))
            {
                row="正文";
            }
            Map<String, String> property=a.getValue();
            Set<Map.Entry<String, String>> entries1 = property.entrySet();
            for(Map.Entry<String, String> b :entries1)
            {
                String p=b.getValue();
                row=row+","+p;
            }//拼接成一行显示信息
            show.add(row);
        }
        return show;
    }
*/

    //提取所有XML中的第一条信息，返回值为list
    public static List<String> xmlToList(Map<String, Map<String, Map<String, String>>> map)
    {
        if(map == null) {

            return null;
        }
        List<String> show=new LinkedList<>();
        Set<Map.Entry<String, Map<String, Map<String, String>>>> entries = map.entrySet();
        for( Map.Entry<String, Map<String, Map<String, String>>> a:entries   )
        {
            Map<String, Map<String, String>> xml=a.getValue();
            List<String> recep=Split.mapToList(xml);
            if(recep!=null)
            {
                String s = recep.get(0);
                show.add(s);
            }
        }
        return show;
    }

}
