package application.repositories;

public class CarRepository {
//    private List<Car> carList = new ArrayList<>();
//
//    // CRUD-методы
//    public void save(Car car) {
//        carList.add(car);
//    }
//    
//    public Car getCarById(int id) {
//        for (Car car : carList) {
//            if (car.getId() == id)
//                return car;
//        }
//        return null;
//    }
//    
//    public List<Car> getAllAvailableCars() {
//        List<Car> availableCars = new ArrayList<>();
//        for (Car car : carList) {
//            if (car.isAvailable())
//                availableCars.add(car);
//        }
//        return availableCars;
//    }
//    
//    public void updateCar(Car updatedCar) {
//        for (int i = 0; i < carList.size(); i++) {
//            if (carList.get(i).getId() == updatedCar.getId()) {
//                carList.set(i, updatedCar);
//                break;
//            }
//        }
//    }
//    
//    public void deleteCar(int id) {
//        carList.removeIf(car -> car.getId() == id);
//    }
//    
//    // Сохранение списка автомобилей в файл
//    public void saveToFile(String filename) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
//            oos.writeObject(carList);
//            System.out.println("Данные автомобилей успешно сохранены в файл: " + filename);
//        } catch (IOException e) {
//            System.err.println("Ошибка при сохранении данных автомобилей: " + e.getMessage());
//        }
//    }
//    
//    // Загрузка списка автомобилей из файла
//    @SuppressWarnings("unchecked")
//    public void loadFromFile(String filename) {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            carList = (List<Car>) ois.readObject();
//            System.out.println("Данные автомобилей успешно загружены из файла: " + filename);
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Ошибка при загрузке данных автомобилей: " + e.getMessage());
//        }
//    }
//    
//    public List<Car> getAllCars() {
//        return carList;
//    }
}
