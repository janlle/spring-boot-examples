package com.andy.log.util;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p> 随机生成中文姓名，性别，Email，手机号，住址
 *
 * @author leone
 * @since 2018-06-02
 **/
public class RandomValue {

    private RandomValue() {
    }

    public static final Random random = new Random();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static final Date date = new Date();

    private static final String fullDatePretty = "yyyy-MM-dd hh:mm:ss";

    private static final String datePretty = "yyyy-MM-dd";

    private static int offset = 0;

    private static final String[] url_suffix = ".com,.cn,.edu,.net,.org".split(",");

    private static final String SEPARATOR_OF_MAC = ":";

    private static final String[] telFirst = "135,139,150,159,168,180".split(",");

    private static final String[] telOther = "23490985,12350867,12520956,15671573,09841252,209839087".split(",");

    private static final String[] driver = "Android,iPhone,MacBook,PC,IPAD,IPod".split(",");

    private static final String base = "abcdefghijklmnopqrstuvwxyz";

    private static final String provinces[] = {"11", "12", "13", "14", "15", "21", "22", "23",
            "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
            "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82"};

    private static final String checks[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "X"};


    private static String[] firstName = {
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "计", "熊", "蒋", "沈", "韩", "杨",
            "朱", "秦", "汪", "许", "何", "吕", "杜", "张", "孔", "曹", "宋", "林", "金", "魏", "陶", "胡",
            "田", "于", "徐", "郭", "姚", "萧", "齐", "贾", "伍", "余", "霍", "董", "戴", "孟", "高", "黄"};

    private static final String[] address = "重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路".split(",");

    private static final String[] email_suffix = "@gmail.com,@yahoo.com,@msn.com,@ask.com,@qq.com,@163.com,@yeah.net,@126.com,@sina.com,@sohu.com".split(",");

    private static String[] messages = {
            "Hi good morning Nice to meet you",
            "Nice to meet you too what do you do please",
            "Yes I like it very much Where do you teach",
            "I teach in No Middle school and you?",
            "I'm a teacher how about you was give her medication",
            "Great! My brother works there too",
            "Good morning may I help you made fun of her",
            "It's very nice of you to invite me on our front porch ",
            "Mr liu would you care for another helping",
            "Did you enjoy the meal that just made it worse",
            "We took our daughter from doctor to doctor but all they did ",
            "Thank you very much for your hospitality",
            "Red cooked carp and rape with fresh mushrooms",
            "Please speak a little louder clearing her throat frequently",
            "It was pretty devastating5 because other children constantly ",
            "We always knew our daughter Kendall was going be a performer of some sort",
            "When Kendall was five we began to notice that she was blinking a lot and ",
            "What's your dream when she was only three or four",
            "She entertained people in our small town by putting on shows ",
            "What sports do you like",
            "I like playing football"};

    private static String[] goods = {"Iphone", "MacBookPro", "IPad", "IWatch", "橙子", "橘子", "香蕉", "橙子"};


    private static final String[] userAgent = {
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 OPR/26.0.1656.60",
            "Mozilla/5.0 (Windows NT 5.1; U; en; rv:1.8.1) Gecko/20061208 Firefox/2.0.0 Opera 9.50",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36",
            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.133 Safari/534.16",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0)",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Maxthon/4.4.3.4000 Chrome/30.0.1599.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 UBrowser/4.0.3214.0 Safari/537.36",
            "Mozilla/5.0 (iPod; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5",
            "Mozilla/5.0 (iPad; U; CPU OS 4_2_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5",
            "Mozilla/5.0 (Linux; U; Android 2.2.1; zh-cn; HTC_Wildfire_A3333 Build/FRG83D) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1",
            "Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10",
            "Mozilla/4.0 (compatible; MSIE 6.0; ) Opera/UCWEB7.0.2.37/28/999",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)"
    };

    //ip范围
    private static int[][] range = {
            {607649792, 608174079},
            {1038614528, 1039007743},
            {1783627776, 1784676351},
            {2035023872, 2035154943},
            {2078801920, 2079064063},
            {-1950089216, -1948778497},
            {-1425539072, -1425014785},
            {-1236271104, -1235419137},
            {-770113536, -768606209},
            {-569376768, -564133889},
    };


    /**
     * 返回Email
     *
     * @return
     */
    public static String randomEmail() {
        int length = random.nextInt(3) + 6;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(email_suffix[(int) (Math.random() * email_suffix.length)]);
        return sb.toString();
    }


    /**
     * 返回手机号码
     */
    public static String randomTel() {
        String first = telFirst[random.nextInt(telFirst.length - 1)];
        return telFirst[random.nextInt(telFirst.length)] + telOther[random.nextInt(telOther.length)];
    }

    /**
     * 返回中文姓名
     */
    public static String randomUsername() {
        StringBuilder sb = new StringBuilder(firstName[random.nextInt(firstName.length)]);
        for (int i = 0; i < random.nextInt(2) + 1; i++) {
            // 定义高低位
            int heightPos, lowPos;
            // 获取高位值
            heightPos = (176 + Math.abs(random.nextInt(39)));
            // 获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (new Integer(heightPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            sb.append(new String(b, Charset.forName("GBK")));
        }
        return sb.toString();
    }

    /**
     * 返回地址
     *
     * @return
     */
    public static String randomAddress() {
        String first = address[random.nextInt(address.length - 1)];
        String second = (random.nextInt(150) + 10) + "号";
        String third = "-" + random.nextInt(150) + 10 + "-" + random.nextInt(150) + 10;
        return first + second + third;
    }

    /**
     * 获取用户
     *
     * @return
     */
    public static Map randomUser() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user_id", offset);
        map.put("username", randomUsername());
        map.put("sex", random.nextBoolean() ? "男" : "女");
        map.put("age", random.nextInt(58) + 12);
        map.put("deleted", random.nextBoolean());
        map.put("tel", randomTel());
        map.put("create_time", randomTime());
        map.put("integral", random.nextInt(9990) + 10);
        offset++;
        return map;
    }

    /**
     * 获取ip
     *
     * @return
     */
    public static String randomIp() {
        int index = random.nextInt(10);
        return numToIp(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
    }

    private static String numToIp(int ip) {
        StringBuilder sb = new StringBuilder();
        int[] b = new int[4];
        String x;
        b[0] = ((ip >> 24) & 0xff);
        b[1] = ((ip >> 16) & 0xff);
        b[2] = ((ip >> 8) & 0xff);
        b[3] = (ip & 0xff);
        return sb.append(b[0]).append(".").append(b[1]).append(".").append(b[2]).append(".").append(b[3]).toString();
    }

    /**
     * 获取随机英文
     *
     * @return
     */
    public static String randomMessage() {
        return messages[random.nextInt(messages.length)];
    }

    /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String randomNum(Integer length) {
        if (length < 1 || length > 512) {
            length = 32;
        }
        StringBuilder result = new StringBuilder();
        final String sources = "0123456789";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            result.append(sources.charAt(rand.nextInt(9)));
        }
        return result.toString();
    }


    /**
     * 生成32位随机数字
     *
     * @param length
     * @return
     */
    public static String randomStr(Integer length) {
        if (length <= 0 || length > 32) {
            length = 32;
        }
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

    /**
     * 获取当前时间戳，单位秒(10位)
     *
     * @return
     */
    public static String timestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取时间字符串
     *
     * @return
     */
    public static String currentDatetime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
    }

    /**
     * 获取随机商品名称
     *
     * @return
     */
    public static String randomGoods() {
        return goods[random.nextInt(goods.length)];
    }

    /**
     * 获取随机url
     *
     * @return
     */
    public static String randomUrl() {
        int length = random.nextInt(5) + 5;
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(url_suffix[(int) (Math.random() * url_suffix.length)]);
        return sb.toString();
    }

    /**
     * 获取随机driver
     *
     * @return
     */
    public static String randomDriver() {
        return driver[random.nextInt(driver.length)];
    }

    /**
     * 获取随机 MAC 地址
     *
     * @return
     */
    public static String randomMac() {
        String[] mac = {
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff)),
                String.format("%02x", random.nextInt(0xff))
        };
        return String.join(SEPARATOR_OF_MAC, mac).toUpperCase();
    }

    /**
     * 生成随机时间
     *
     * @return
     */
    public static String randomTime() {
        long l = (long) random.nextInt(3650) * 1000 * 60 * 60 * 24 + random.nextInt(100000000);
        return sdf.format(new Date(System.currentTimeMillis() - l));
    }

    /**
     * 生成随机时间
     *
     * @return
     */
    public static Date randomDate() {
        long l = (long) random.nextInt(3650) * 1000 * 60 * 60 * 24 + random.nextInt(100000000);
        return new Date(System.currentTimeMillis() - l);
    }

    public static long currentTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Long.parseLong(localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
    }

    /**
     * 获取随机UUID
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取随机身份证号码
     *
     * @return
     */
    public static String randomIDCard() {
        StringBuffer sb = new StringBuffer();
        // 1-2 随机生成省、自治区、直辖市代码
        String province = provinces[random.nextInt(provinces.length - 1)];
        // 3-4 随机生成地级市、盟、自治州代码
        String city = String.format("%02d", 18);
        // 5-6 随机生成县、县级市、区代码
        String county = String.format("%02d", 28);
        // 7-14 随机生成出生年月
        String birth = randomBirth();
        // 15-17 随机生成性别
        String no = String.format("%03d", random.nextInt(1000));
        // 18 随机生成校验码
        String check = checks[random.nextInt(checks.length - 1)];
        // 拼接身份证号码
        sb.append(province).append(city).append(county).append(birth).append(no).append(check);
        return sb.toString();
    }

    /**
     * 随机生成minAge到maxAge年龄段的人的生日日期
     *
     * @return
     */
    public static String randomBirth() {
        sdf.applyPattern("yyyyMMdd");
        long l = 31536000000L * (random.nextInt(72) + 1);
        return sdf.format(new Date(System.currentTimeMillis() - l - random.nextInt(1000000000)));
    }

    /**
     * 获取随机userAgent
     *
     * @return
     */
    public static String randomUserAgent() {
        return userAgent[random.nextInt(userAgent.length)];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            System.out.println(currentTimestamp());
        }
    }
}

