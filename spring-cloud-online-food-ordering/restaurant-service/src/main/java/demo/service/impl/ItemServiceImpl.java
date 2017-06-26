package demo.service.impl;

import demo.domain.ItemInfo;
import demo.domain.ItemRepository;
import demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<ItemInfo> showMenu(String restaurantId) {
        return this.itemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<ItemInfo> saveMenu(List<ItemInfo> itemList) {
        return this.itemRepository.save(itemList);
    }

    @Override
    public void deleteall(){
        this.itemRepository.deleteAll();
    }
}
