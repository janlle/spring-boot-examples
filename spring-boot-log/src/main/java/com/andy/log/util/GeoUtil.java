package com.andy.log.util;

import com.andy.log.app.entity.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 地理工具类，实现通过ip查找地址区域
 */
public class GeoUtil {

    private static String pattern = "(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";

    private static Reader reader;

    private static File file;

    static {
        try {
            file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "GeoLite2-City.mmdb");
            // ClassPathResource classPathResource = new ClassPathResource("GeoLite2-City.mmdb");
            reader = new Reader(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得国家数据
     */
    public static String getCountry(String ip) {
        if (!matchIp(ip)) {
            return null;
        }
        try {
            DatabaseReader reader = new DatabaseReader.Builder(file).build();
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(ipAddress);
            return response.getCountry().getNames().get("zh-CN");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获得国家数据
     */
    public static String getProvince(String ip) {
        if (!matchIp(ip)) {
            return null;
        }
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("subdivisions").get(0).get("names").get("zh-CN").textValue();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获得国家数据
     */
    public static String getCity(String ip) {
        if (!matchIp(ip)) {
            return null;
        }
        try {
            JsonNode node = reader.get(InetAddress.getByName(ip));
            return node.get("city").get("names").get("zh-CN").textValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得国家数据
     */
    public static Location getLocation(String ip) {
        if (!matchIp(ip)) {
            return null;
        }
        try {

            JsonNode node = reader.get(InetAddress.getByName(ip));
            Location location = new Location();
            location.setCountry(getCountry(ip));
            location.setProvince(getProvince(ip));
            return location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 校验ip地址
     *
     * @param ip
     * @return
     */
    public static boolean matchIp(String ip) {
        if (Objects.nonNull(ip)) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(ip);
            return r.matcher(ip).matches();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(matchIp(" 120.197.48.146"));
    }

}
