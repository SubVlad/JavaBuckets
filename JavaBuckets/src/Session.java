import java.util.Scanner;

// Этот класс пока не используется. Я его сделал заранее, так как хотел, чтобы каждому эскперименту создавалась отдельная сессия. Сейчас это просто рудимент от старой версии, в которой вся информация и ввод данных осуществлялись через консоль
public class Session {
    public double taskNumber;

    public Session()
    {
        Scanner in = new Scanner(System.in);
        Bucket[] buckets = new Bucket[2];
        System.out.println("Добро пожаловать в JavaBuckets!\nСейчас вам будет предоставлена возможность ввести объёмы вёдер и искомый объём воды. \nПо окончанию вычисления будет выдана подробная информация о процедуре. \nобъём первого ведра: ");
        int num0 = in.nextInt();
        System.out.println("объём второго ведра: ");
        int num1 = in.nextInt();
        System.out.println("искомый объём воды: ");
        int res = seek(num0, num1);
        buckets[0] = new Bucket(num0);
        buckets[1] = new Bucket(num1);
        calculate(buckets[0], buckets[1], res);



    }

    public static double getRandom()
    {
        double r = Math.random();
        if(r > 0.6){
            r = getRandom();
        }
        return r;
    }
    public int seek(int num0, int num1)
    {
        Scanner in = new Scanner(System.in);
        int sought;
        sought = in.nextInt();
        if(sought > num0 || sought > num1){
            System.out.println("Неверно! Искомый объём не может быть больше любого из ведер. Введите иной объём: ");
            sought = seek(num0, num1);
        }
        return sought;
    }
    public void calculate(Bucket a, Bucket b, int res)
    {
        String lg = new String();
        lg = "Подсказка: левым числам соответсвуют объёмы вёдер, правым числам - объём воды в них\n";
        int st = 0;
        double taskIndex = -1;
        for(int i = 0; i < 2000000; i ++){

            taskNumber = Math.floor(getRandom() * 10);
            if(taskNumber == 0 && a.curvol != a.vol && b.curvol != b.vol && taskIndex != 2){
                a.fillBucket();
                lg = lg + "наполнил первое ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 1 && a.curvol != 0  && b.curvol != b.vol && taskIndex != 4){
                a.pourInAB(b);
                lg = lg + "вылил из первого ведра во второе. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 2 && a.curvol != 0 && taskIndex != 0){
                a.pourOut();
                lg = lg + "опорожнил первое ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 3 && b.curvol != b.vol && a.curvol != a.vol && taskIndex != 5){
                b.fillBucket();
                lg = lg + "наполнил второе ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 4 && b.curvol != 0  && a.curvol != a.vol && taskIndex != 1){
                b.pourInAB(a);
                lg = lg + "вылил из второго ведра в первое. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(taskNumber == 5 && b.curvol != 0 && taskIndex != 3){
                b.pourOut();
                lg = lg + "опорожнил второе ведро. В вёдрах: \n" + a.vol + " " + a.curvol + "\n" + b.vol + " " + b.curvol + "\n";

                st ++;
                taskIndex = taskNumber;
            }
            if(a.curvol == 0 && b.curvol == 0){
                i = 0;
                st = 0;
                lg = "Подсказка: левым числам соответсвуют объёмы вёдер, правым числам - объём воды в них\n";
                taskIndex = -1;
            }
            if(a.curvol == res || b.curvol == res){
                lg = lg + "Победа!!! Задача выполнена за " + st + " шагов";
                System.out.println(lg);
                taskIndex = -1;
                break;
            }
        }
    }

}
