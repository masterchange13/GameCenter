package com.mao.practise617;

public class IpAddr {
    private static final String ip = "http://121.40.147.140:8083";
//    private static final String ip = "http://192.168.1.226:8083";
    private static final String ipGameDetail = ip + "/gameGuide";
    public static final String  ipGameStar = ip + "/gameStar";
    public static final String  ipGameRecommend = ip + "/gameRecommend";
    public static final String  ipGame = ip + "/gameGame";
    public static final String  ipGameComment = ip + "/gameComment";

    public String getIp(){
        return ip;
    }

    public String getIpGameDetail(){
        return ipGameDetail;
    }

    public String getIpGameStar(){
        return ipGameStar;
    }

    public String getIpGameRecommend(){
        return ipGameRecommend;
    }

    public String getIpGame(){
        return ipGame;
    }

    public String getIpGameComment(){
        return ipGameComment;
    }
}
