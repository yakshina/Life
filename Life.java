public class Life {

    final int sizeField = 15;  // размер поля
    boolean[][] field;
    boolean[][] nextField;
    final int numberOfGenerations = 5;   //количество поколений

    public static void main(String[] args) {
        new Life().game();
    }

    Life() {
        //конструктор инициализации полей
        field = new boolean[sizeField][sizeField];
        nextField = new boolean[sizeField][sizeField];
    }

    //запуск жизни

    void game() {
        Initial();
        for (int i = 1; i < numberOfGenerations; i++) {
            System.out.println("Поколение " + i);
            OneStep();
        }
    }

    // задание начального состояния
    void Initial() {
        //заполнение массива для начального состояния из задания
        field[5][7] = field[6][7] = field[8][7] = field[9][7] = field[8][8] = field[6][8] = field[7][9]
                = field[7][5] = field[8][6] = field[6][6] = true;
        //выводим заполненый массив на экран
        printField();
    }


    //алгоритм для итерации
    void OneStep() {
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                int count = neighbors(i, j);
                nextField[i][j] = field[i][j];
                //если у пустой клетки =3 соседа, то в клетки появится жизнь
                nextField[i][j] = (count == 3) ? true : nextField[i][j];
                //если у живой клетки <2 или >3 соседей, то в клетка умрет
                nextField[i][j] = ((count < 2) || (count > 3)) ? false : nextField[i][j];

            }
        }
        for (int i = 0; i < sizeField; i++) {
            for (int j = 0; j < sizeField; j++) {
                field[i][j] = nextField[i][j];
            }
        }
        printField();
    }

    void printField() {
        //вывод массива

        for (int j = 0; j < sizeField; j++) {
            System.out.println();
            for (int i = 0; i < sizeField; i++)
                if (field[i][j]) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
        }
        System.out.println();
        System.out.println();
    }


    //считаем соседей
    int neighbors(int i, int j) {
        int c = 0; //счетчик
        for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, sizeField - 1); x++) {
            for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, sizeField - 1); y++) {
                if (x != i || y != j) {
                    if (field[x][y]) {
                        c++;
                    }
                }
            }
        }
        return c;
    }


}
