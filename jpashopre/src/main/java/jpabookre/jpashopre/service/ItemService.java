package jpabookre.jpashopre.service;

import jpabookre.jpashopre.controller.BookForm;
import jpabookre.jpashopre.domain.item.Book;
import jpabookre.jpashopre.domain.item.Item;
import jpabookre.jpashopre.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item finOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void update(BookForm form){

        Item item = itemRepository.findOne(form.getId());
        if(item.getDtype().equals("Book")){
            Book book = (Book) item;
            book.setName(form.getName());
            book.setPrice(form.getPrice());
            book.setIsbn(form.getIsbn());
            book.setStockQuantity(form.getStockQuantity());
            book.setAuthor(form.getAuthor());
        }
    }


}
