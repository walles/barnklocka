package com.gmail.walles.johan.johansbarnklocka;

class TimeReadout {
    private static final String[] MINUTE_FORMATS = new String[] {
            "Klockan H0",
            "En minut över H0",
            "Två minuter över H0",
            "Tre minuter över H0",
            "Fyra minuter över H0",
            "Fem över H0",
            "Sex minuter över H0",
            "Sju minuter över H0",
            "Åtta minuter över H0",
            "Nio minuter över H0",
            "Tio över H0",
            "Elva minuter över H0",
            "Tolv minuter över H0",
            "Tretton minuter över H0",
            "Fjorton minuter över H0",
            "Kvart över H0",
            "Sexton minuter över H0",
            "Sjutton minuter över H0",
            "Arton minuter över H0",
            "Nitton minuter över H0",
            "Tjugo över H0",
            "Tjugoen minuter över H0",
            "Tjugotvå minuter över H0",
            "Tjugotre minuter över H0",
            "Tjugofyra minuter över H0",
            "Fem i halv H1",
            "Tjugosex minuter över H0",
            "Tjugosju minuter över H0",
            "Tjugoåtta minuter över H0",
            "Tjugonio minuter över H0",
            "Halv H1",
            "En minut över halv H1",
            "Två minuter över halv H1",
            "Tre minuter över halv H1",
            "Fyra minuter över halv H1",
            "Fem över halv H1",
            "Sex minuter över halv H1",
            "Sju minuter över halv H1",
            "Åtta minuter över halv H1",
            "Nio minuter över halv H1",
            "Tjugo i H1",
            "Nitton minuter i H1",
            "Arton minuter i H1",
            "Sjutton minuter i H1",
            "Sexton minuter i H1",
            "Kvart i H1",
            "Fjorton minuter i H1",
            "Tretton minuter i H1",
            "Tolv minuter i H1",
            "Elva minuter i H1",
            "Tio i H1",
            "Nio minuter i H1",
            "Åtta minuter i H1",
            "Sju minuter i H1",
            "Sex minuter i H1",
            "Fem i H1",
            "Fyra minuter i H1",
            "Tre minuter i H1",
            "Två minuter i H1",
            "En minut i H1",
    };

    private static final String[] HOUR_NAMES = new String[] {
            "tolv",
            "ett",
            "två",
            "tre",
            "fyra",
            "fem",
            "sex",
            "sju",
            "åtta",
            "nio",
            "tio",
            "elva",
    };

    public String format(int hour, int minute) {
        String h0 = HOUR_NAMES[hour % 12];
        String h1 = HOUR_NAMES[(hour + 1) % 12];

        return MINUTE_FORMATS[minute].replace("H0", h0).replace("H1", h1);
    }
}
