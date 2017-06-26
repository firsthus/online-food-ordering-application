package demo.service;


import demo.domain.ItemInfo;

import java.util.List;

public interface ItemService {
    List<ItemInfo> showMenu(String restaurantId);
    List<ItemInfo> saveMenu(List<ItemInfo> itemList);
    void deleteall();
}
