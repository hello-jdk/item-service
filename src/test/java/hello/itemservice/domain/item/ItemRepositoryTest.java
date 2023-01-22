package hello.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository = new ItemRepository();
    }

    @Test
    void save() {
        Item itemA = new Item("itemA", 10000, 10);

        Item saveItem = itemRepository.save(itemA);

        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 5);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> items = itemRepository.findAll();
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    void update() {
        Item itemA = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(itemA);
        Long itemId = saveItem.getId();

        Item itemB = new Item("item2", 20000, 20);
        itemRepository.update(itemId, itemB);

        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(itemB.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(itemB.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(itemB.getQuantity());
    }

}