# VladimirProjectJava
Проект написан на языке программирования Java с ичпользованием Maven для внедрения зависимостей.
Подключение к БД выполняется с помощью интерфейса Connection.
Запросы же к БД выполняются с помощью интерфеса Statement.
После запуска проекта пользователь должен ввести абсолютный путь к csv-файлу c данными и указать были ли эти данные заранее подгружены в нашу SQLite.
![image](https://user-images.githubusercontent.com/95271008/211210912-6d1ba855-de25-4784-a97b-79607a444dcd.png)
После выполнения вышеописанных действий в дело вступает сочетание классов Game и GameController.
Класс Game выступает как вспомогательный для хранения данных из нашего датасета.
![image](https://user-images.githubusercontent.com/95271008/211211010-619102a9-c076-43c0-9e65-093f0ebe9118.png)
Все поля данного класса совпадают со столбцами нашему исходного файла, а так же для каждого из полей прописаны геттеры и сеттеры и переопределен метод toString() для корректного отображения данных.
Класс GameController необходим для манипулирования этими данными.
Все методы данного класса направлены на взаимодействие с нашей базой данных.
Метод firstTask() занимается построением графика для первого задания, предварительно сделав запрос на необходимую выборку данных из нашей БД.
![image](https://user-images.githubusercontent.com/95271008/211211111-536a7b00-0db6-4ad0-a273-a8d4e0f3832b.png)
Метод secondTask() так же выполняет запрос к нашей БД с использование вышеописанных интерфесов. Полученная выборка формаируется на основе выбранного из задания года.
Далее просиходит поиск максимального значения продаж и выводится объект класса Game с полями, сформированными на основе полученной выборки данных.
![image](https://user-images.githubusercontent.com/95271008/211211166-4d754be2-982d-47aa-ae41-6b43da31c736.png)
Метод thirdTask() ищет строку с макимальными продажами в Японии в оперделенном диапазоне (оператор BETWEEN).
![image](https://user-images.githubusercontent.com/95271008/211211201-49ac219a-9dcd-4a0e-8b41-b5906e817a38.png)
Остальные методы в классе занимаются подрузкой данных из нашего csv-файла в БД.
В каждом из методом подключен Logger из стандартной библиотке java.util для контроля ошибок, возникающих в ходе работе нашей программы.
