package pl.sda;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* Quiz
         * Naszym zadaniem jest napisanie aplikacji do przeprowadzania quizów z zamkniętymi odpowiedziami.
         * każde uruchomienie aplikacji wygląda następująco:
         * 1. Witamy gracza, pytamy co chce robić: grać, zobaczyć tablicę wyników czy wyjść
         * 2. Jeśli chce grać:
         *      a. pytamy o nick (przyda się przy tablicy wyników)
         *      b. zadajemy 5 losowych pytań z tablicy pytań - po nich wraca do punktu 2. (pytamy go co chce robić)
         *          ba. za każdym razem informujemy gracza czy odpowiedział na pytanie dobrze czy źle (i wyświetlamy poprawną odpowiedź)
         *          bb. dodajmy punktację dobra odpowiedź - 1 punkt, zła - 0 punktów
         *          bc. na koniec gracz powinien dostać informację o tym ile zebrał punktów
         *          bd. ta informacja powinna również zostać zapisana w tablicy wyników
         * 3. Jeśli chce zobaczyć tablicę wyników - wyświetlamy tablicę wyników graczy (zawsze ten, który ma najwięcej punktów na górze)
         */

        /* Wskazówki implementacji:
         * 1. W części kodu odpowiedzialnej za obsługę grania zapytaj gracza o nick (używając scannera), przypisz go do zmiennej playerName - użyjemy później
         * 2. Utwórz klasę Question - powinna mieć pole przechowujące pytanie, tablicę z odpowiedziami i pole przechowujące numer prawidłowej odpowiedzi
         *   a. W konstruktorze Question do pola z tablicą przypisz tablicę 4 elementową
         *   b. Dodaj gettery i settery do powyższych pól
         * 3. Utwórz klasę QuestionBase - powinna mieć tablicę z pytaniami (a więc tablicę Question) i pole z aktualną liczbą wprowadzonych pytań
         *    a. W konstruktorze do pola z tablicą przypisz tablicę 100 elementową
         *    b. Utwórz metodę addQuestion - dodającą pytanie do powyższej tablicy za ostatnim wprowadzonym pytaniem (wiemy, która pozycja jest wolna, bo mamy liczbę wprowadzonych)
         *    c. Utwórz metodę getQuestion przyjmującą w parametrze int - powinna zwracać pytanie z pozycji przekazanej w parametrze
         *    d. Utwórz metodę questionCount - powinna zwrócić ile mamy pytań (a więc tylko zwrócić wartość pola) - to zwyczajny getter
         *  4. W klasie Main w metodzie main w miejscu "<tu inicjalizacja pytan>" utworz instancję QuestionBase i przypisz ją do zmiennej.
         *  5. Używając metody addQuestion dodaj do powyższej zmiennej pytania (na początek 10 wystarczy). Powinno to wyglądać mniej więcej tak:
         * Question question = new Question();
         * question.setQuestion("Ile lap ma kot?")
         * question.getAnswers()[0] = "Jedną";
         * question.getAnswers()[1] = "Cztery";
         * question.getAnswers()[2] = "Trzy";
         * question.getAnswers()[3] = "Dwie";
         * question.setCorrectAnswer(1);
         * questionBase.add(question);
         *  6. Cały kod dodający pytania do bazy pytań (a więc nie inicjalizacja QuestionBase) przenieś do osobnej metody
         *  statycznej i prywatnej - initializeQuestionBase(QuestionBase questionBase)
         *  7. Zaimplementuj obsługę grania w "tutaj kod do obsługi grania":
         *     a. W pętli, która będzie się wykonywała 5 raz wykonaj następne punkty (5, bo tylko tyle pytań mamy zadać)
         *     b. Losuj liczbę pomiędzy 0, a liczą pytań z QuestionBase (możemy się do tej liczby dobrać używając gettera z questionBase)
         *     c. Przy pomocy metody getQuestion wyciągnij z QuestionBase pytanie o wylosowanym numerze
         *     d. Zadawaj pytanie - wyświetl pytanie i 4 możliwe odpowiedzi
         *     e. Poproś użytkownika o udzielenie numeru odpowiedzi
         *     f. Poinformuj użytkownika o tym która odpowiedź była dobra (wyświetl ją) i czy udzielił poprawnej odpowiedzi
         *     g. Powtórz od punktu b (5 razy - to zapewni nam pętla z a.)
         *  8. Och nie, zapomnieliśmy o punktacji! - implementacja tego punktu wspólnie
         */
        //TODO: jak można łatwo podzielić klasy - POJO i nie

        // <tu inicjalizacja pytan>
        QuestionBase questionBase = new QuestionBase();

        initializeQuestionBase(questionBase);


        System.out.println("Witaj! Co chcesz robić?");
        Scanner scanner = new Scanner(System.in);

        String menuChoice;
        do {
            menuChoice = scanner.nextLine();
            if ("gram".equals(menuChoice)) {
                System.out.println("Podaj swoj nick");
                String playerName = scanner.nextLine();
                int questionCounter = 0;

                int[] usedRandomNumbers = new int[5];
                while (questionCounter < 5) {
                    Random randomGenerator = new Random();
                    int questionNumber = randomGenerator.nextInt(questionBase.getQuestionCount());
                    while (doesArrayContain(usedRandomNumbers, questionCounter, questionNumber)) {
                        questionNumber = randomGenerator.nextInt(questionBase.getQuestionCount());
                    }
                    usedRandomNumbers[questionCounter] = questionNumber;

                    //TODO: jak zrobic, zeby sie nie powtarzaly

                    Question chosenQuestion = questionBase.getQuestion(questionNumber);
                    System.out.println(chosenQuestion.getText());
                    String[] answers = chosenQuestion.getAnswers();
                    for (int i = 0; i < answers.length; i++) {
                        int currentQuestionNumber = i + 1;
                        System.out.println(currentQuestionNumber + ". " + answers[i]);
                    }
                    System.out.println("Podaj odpowiedz (cyfra)");
                    String playerAnswer = scanner.nextLine();
                    int playerAnswerIndex = Integer.parseInt(playerAnswer) - 1;
                    int correctAnswerIndex = chosenQuestion.getCorrectAnswerIndex();
                    if (playerAnswerIndex == correctAnswerIndex) {
                        System.out.println("Poprawna odpowiedz!");
                    } else {
                        System.out.println("Niepoprawna odpowiedz!");
                        System.out.println("Poprawna odpowiedz to: ");
                        System.out.println(answers[correctAnswerIndex]);
                    }
                    questionCounter ++;
                }
            } else if ("tablica".equals(menuChoice)) {
                //tutaj kod do obsługi tablicy wyników
            }
        } while ("q".equals(menuChoice));
    }

    private static void costam(int abc) {
        abc++;
        System.out.println(abc);
    }

    private static boolean doesArrayContain(int[] arrayToLookIn, int arrayEnd, int searchingFor) {
        for(int i = 0; i < arrayEnd; i++) {
            if(arrayToLookIn[i] == searchingFor) {
                return true;
            }
        }
        return false;
    }

    private static void initializeQuestionBase(QuestionBase questionBase) {
        Question question = new Question();
        question.setText("Ile lap ma pies?");
        String[] answers = question.getAnswers();
        answers[0] = "Jedną";
        answers[1] = "Cztery";
        answers[2] = "Trzy";
        answers[3] = "Dwie";
        question.setCorrectAnswerIndex(1);
        questionBase.addQuestion(question);


        question = new Question();
        question.setText("Ile lap ma kot?");
        answers = question.getAnswers();
        answers[0] = "Jedną";
        answers[1] = "Cztery";
        answers[2] = "Trzy";
        answers[3] = "Dwie";
        question.setCorrectAnswerIndex(1);
        questionBase.addQuestion(question);

        question = new Question();
        question.setText("O ktorej zaczynamy zajecia w GB?");
        answers = question.getAnswers();
        answers[0] = "8:00";
        answers[1] = "9:00";
        answers[2] = "12:00";
        answers[3] = "13:33";
        question.setCorrectAnswerIndex(0);
        questionBase.addQuestion(question);

        question = new Question();
        question.setText("Gdzie sa zajecia w GB?");
        answers = question.getAnswers();
        answers[0] = "Londyn";
        answers[1] = "Katowice";
        answers[2] = "Bristol";
        answers[3] = "Warszawa";
        question.setCorrectAnswerIndex(0);
        questionBase.addQuestion(question);

        question = new Question();
        question.setText("Jaka mamy dzis pogode?");
        answers = question.getAnswers();
        answers[0] = "Deszczowa";
        answers[1] = "Sloneczna";
        answers[2] = "Pochmurnie";
        answers[3] = "Wietrzna";
        question.setCorrectAnswerIndex(0);
        questionBase.addQuestion(question);
    }

}
