package com.example.demoSan.models;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class Procedure {
    private int id, price, schedule;
    private String name;
    private Time start_at, end_at;
    final String pattern = "HH:mm";
    final String[] Weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public Procedure(){}

    public Procedure(int id, int price, int schedule, String name, Time start_at, Time end_at) {
        this.id = id;
        this.price = price;
        this.schedule = schedule;
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStart_at() {
        return start_at;
    }

    public String getStringStart_at() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.start_at);
    }

    public void setStart_at(Time start_at){
        this.start_at = start_at;
    }

    public Time getEnd_at() {
        return end_at;
    }

    public String getStringEnd_at() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.end_at);
    }

    public void setEnd_at(Time end_at) {
        this.end_at = end_at;
    }

    // представляет значение из БД в виде списка, который заполняет значениями двойки в степенях (в зависимости от дня недели)
    public double[] getScheduleArray(){
        double listDays[] = new double[7];
        for (int i=6; i>=0; i--){
            if(schedule-(Math.pow(2,i))>=0){
                listDays[i] = Math.pow(2,i);
                schedule -= Math.pow(2,i);
            }
        }
        return listDays;
    }

    // представляет вычисленный список с числовыми значениями ↑ в виде списка из дней недели, в которые проводится процедура
    public List<String> getDaysOfWeekArray(){
        double[] listDays = getScheduleArray();
        List<String> AllWeekDays = new ArrayList<>();
        for (int i=0; i<7; i++){
            if(listDays[i]!=0){
                AllWeekDays.add(Weekdays[i]);
            }
        }
        return AllWeekDays;
    }

    // превращает полученный список ↑ в строку
    public String getDaysOfWeekString(){
        String list = getDaysOfWeekArray().toString();
        return list.substring(1,list.length()-1);
    }

    // вычисляет ближайший день, в который проводится процедура
    public String upcoming(){
        // инициализируем необходимые переменные
        double[] listDays = getScheduleArray();
        String upcoming = null;
        // строка для хранения первого в расписании дня проведения процедуры
        String first_day = null;

        // получаем настоящее время и время начала процедуры в строковом типе
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        Date currentDate = new Date();
        String currentTime = parser.format(currentDate);
        String start_at = parser.format(getStart_at());
        Date now = null, start = null;
        try {
            start = parser.parse(start_at);
            now = parser.parse(currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // список для хранения значений дней недели. необходим, тк Calendar вытаскивает дни недели со значениями:
        // 1 - вскр, 2 - пн, и тд... с помощью этого списка преобразуем эти числа, вытаскивая их по индексу.
        // таким образом, 0 (индекс элемента 2, т.е. ПН по Calendar) - пн, 6 (индекс элемента 1, т.е. ВС по Calendar) - вскр
        List<Integer> daysOfWeekCalendar = Arrays.asList(2, 3, 4, 5, 6, 7, 1);
        Date today = Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        // переменная для хранения значения сегодняшнего дня недели (от в диапазоне (2,3,4,5,6,7,1), соответственно равным пн-вскр)
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        // массив для хранения буллевых (0,1) значений
        // если в определенный день недели процедура не проводится, 0
        // если проводится, 1
        int[] daysOfWeek = new int[7];

        // проходимся по индексам дней недели. допустим, что индекс ПН=0, ВС=6
        for (int i=0; i<7; i++){
            if (listDays[i] != 0){
                daysOfWeek[i] = 1;
            }
            else {
                daysOfWeek[i] = 0;
            }
        }

        // ПРИМ: этот день недели - день недели с индексом i, который проверяется циклом
        // другими словами, этот день недели = Weekdays[i]
        for (int i=0; i<7; i++){

            if (i==6){
                upcoming = "on " + first_day;
                break;
            }
            // если в этот день недели процедуры нет, то программа переходит к следующему дню недели
            if (daysOfWeek[i] == 0){
                continue;
            }
            // если этот день является первым днём в неделе, когда процедура проводится,
            // то сохраняем название этого дня недели в переменную
            // * нужно в ситуации, когда настоящий день недели - позже дней, в которые проводится процедура.
            // * тогда программа выводила бы последний день проведения процедуры.
            // НАПРИМЕР: процедура проводится в ПН и ЧТ. сегодня - СБ. программа запомнила бы ЧТ и вывела его.
            //           а нам нужно, чтобы программа выводила ПН.
            if(isFirstDayOfProcedure(daysOfWeek, i)){
                first_day = Weekdays[i];
            }
            // если сегодняшний день недели, день проведения процедуры совпадают и этот день недели совпадают,
            // проверяем по времени
            if (daysOfWeekCalendar.indexOf(dayOfWeek) == i){
                // если время процедуры прошло, то переходим к следующему дню недели
                if (now.after(start)){
                    continue;
                }
                // если время процедуры не настало, то присваиваем значение СЕГОДНЯ и выводим
                upcoming = "Today";
                break;
            }
            upcoming = "on " + Weekdays[i];
        }
        return upcoming;
    }

    // метод для проверки, является ли выбранный день недели (i=Weekdays[i]) - первым днём проведения процедуры
    // на неделе
    // передаём методу также массив буллевых значений дней недели (когда нет процедуры - 0, когда есть - 1)
    public boolean isFirstDayOfProcedure(int[] listDays, int i){
        // создаём подмассив, содержащий в себе дни до выбранного дня недели
        int[] daysBefore = new int[i+1];
        // делаем массив на один элемент больше, который вставляем в начало и присваиваем ему 0.
        // * нужно для определения ПН (с 0 индексом) первым днём недели
        daysBefore[0] = 0;
        // заполняем значения массива с 0 до i значениями переданного массива
        for (int j=1; j<i+1; j++){
            daysBefore[j] = listDays[j-1];
        }
        // функция для проверки, есть ли в полученном массиве 1.
        // т.о., определим, является ли выбранный день недели первым
        boolean contains = IntStream.of(daysBefore).anyMatch(x->x==1);
        // ложь - процедура проходила в день недели до выбранного дня
        // правда - процедура ещё не проводилась
        if (contains) return false;
        else return true;
    }
}
