package com.coconet.server.define;

import org.springframework.stereotype.Repository;

@Repository
public class Status {

    public final int WORK_START = 10; // (로그인시)근무중
    public final int WORK_OUTWORK = 11; // 외근
    public final int WORK_BREAK = 12; // 휴식
    public final int WORK_SITETRIP = 13; // 출장
    public final int WORK_DAYOFF = 14; // 휴가
    public final int WORK_NOTHING = 15; // (로그아웃시)출근전 상태로 Setting

    public final int WORK_OUTWORK_START = 111; // 외근 신청 상태 - (로그인시)근무중
    public final int WORK_OUTWORK_NOTHING = 112; // 외근 신청 상태 - (로그아웃시)출근전 상태로 Setting
    public final int WORK_SITETRIP_START = 131; // 출장 신청 상태 - (로그인시)근무중
    public final int WORK_SITETRIP_NOTHING = 132; // 출장 신청 상태 - (로그아웃시)출근전 상태로 Setting


    // 타이틀 얻어오기
    public String getStatus(int id)
    {
        switch (id)
        {
            case WORK_START:
            case WORK_OUTWORK_START:
            case WORK_SITETRIP_START:
                return "[WORK_START]";

            case WORK_OUTWORK:
                return "[WORK_OUTWORK]";

            case WORK_BREAK:
                return "[WORK_BREAK]";

            case WORK_SITETRIP:
                return "[WORK_SITETRIP]";

            case WORK_DAYOFF:
                return "[WORK_DAYOFF]";

            case WORK_NOTHING:
            case WORK_OUTWORK_NOTHING:
            case WORK_SITETRIP_NOTHING:
                return "[WORK_FINISH]";

            default:
                return null;
        }
    }

    public String getColor(String Status)
    {
        switch (Status)
        {
            case "출근":
                return "#2CB0D7";

            case "외근":
                return "#2C89DE";

            case "휴식":
                return "#BFC8D2";

            case "출장":
                return "#6571DC";

            case "휴가":
                return "#F0D828";

            case "퇴근":
                return "#BFC8D2";

            default:
                return null;
        }
    }
}
