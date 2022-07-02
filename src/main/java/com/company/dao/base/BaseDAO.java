package com.company.dao.base;


import com.google.gson.Gson;
import lombok.Getter;

@Getter
public class BaseDAO {
    protected final Gson gson = new Gson();
//    @SuppressWarnings("unchecked")
//    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
//            .getGenericSuperclass())
//            .getActualTypeArguments()[0];
//    protected final List<T> genericList = load();
//
//    private List<T> load() {
//        String fileName = entityClass.getSimpleName().toLowerCase();
//        try (InputStream is = new FileInputStream("src\\main\\resources\\db\\%s.json".formatted(fileName))) {
//            String jsonSTRING = new String(is.readAllBytes());
//            return gson.fromJson(jsonSTRING, new TypeToken<List<User>>() {
//            }.getType());
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public void persist() {
//        String fileName = entityClass.getSimpleName().toLowerCase();
//        try (OutputStream is = new FileOutputStream("src\\main\\resources\\db\\%s.json".formatted(fileName))) {
//            String jsonSTRING = gson.toJson(genericList);
//            is.write(jsonSTRING.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}