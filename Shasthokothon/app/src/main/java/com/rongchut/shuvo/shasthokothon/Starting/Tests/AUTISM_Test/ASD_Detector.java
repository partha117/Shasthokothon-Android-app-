package com.rongchut.shuvo.shasthokothon.Starting.Tests.AUTISM_Test;

/**
 * Created by ASUS on 28-Dec-16.
 */


public class ASD_Detector {
    private boolean condition[];

    public ASD_Detector(boolean[] condition) {
        this.condition = condition;
    }

    public boolean[] getCondition() {
        return condition;
    }

    public void setCondition(boolean[] condition) {
        this.condition = condition;
    }

    /*for only 2,5,12 if yes then count 1
      for others if no count1
       if 2&5&12 yes then asd
       if 1&3&4&6&7&9&10&11&13&14&15&16&17&18&19&20 no then ASD
       point 0-2 low risk if age<24 months needs a 2nd check after 2nd birthday
       point  3-7 needs to consult a health worker or doctor
       point 8-20 high risk it is as good as fast they consult doctor
       */


    public String getReport()
    {
        boolean ASD=false;
        boolean temp=(condition[0]);
        int point=0;
        String text="";
        for(int i=0;i<20;i++)
        {
            if((i!=1)&&(i!=4)&&(i!=11))
            {
                temp = temp || (condition[i]);
            }
        }
        if((condition[1]&&condition[4]&&condition[11])||(!temp))
        {
            ASD=true;
        }
        for(int i=0;i<20;i++)
        {
            if((i==1)||(i==4)||(i==11))
            {
                if (condition[i])
                {
                    point++;
                }
            }
            else
            {
                if(!condition[i])
                {
                    point++;
                }
            }
        }
        if((point>=0)&&(point<=2))
        {
            if(ASD) {
                text = "যদি আপনার সন্তানের বয়স  ২ বছরের কম হয় তবে২ বছর বয়স পুরন হওয়ার পর আবার পরীক্ষা করুন।আপাতত সে বিশেষ চাহিদা সম্পন্ন শিশু(অটিজম আক্রান্ত) নয় বলে চিহ্নিত হয়েছে";
            }
            else
            {
                text = "আপনার সন্তান বিশেষ চাহিদা সম্পন্ন শিশু(অটিজম আক্রান্ত) নয় বলে চিহ্নিত হয়েছে";
            }


        }
        else if((point>=3)&&(point<=7))
        {
            text="আপনার সন্তান অটিজমের মাঝারি ঝুঁকিতে রয়েছে।আপনার উচিত তাকে একজন স্বাস্থ্যকর্মী আথবা ডাক্তার দ্বারা পরীক্ষা করান";
        }
        else if((point>=8)&&(point<=20))
        {
            text="আপনার সন্তান অটিজমের অতি শঙ্কিত মাত্রার ঝুঁকিতে রয়েছে।আপনারঅতিসত্তর একজন ডাক্তারের সরনাপন্ন হওয়া উচিত";
        }
        return  text;
    }
}
