package jpabookre.jpashopre.controller;

import jpabookre.jpashopre.domain.item.Book;
import jpabookre.jpashopre.domain.item.Item;
import jpabookre.jpashopre.domain.item.Movie;
import jpabookre.jpashopre.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute BookForm form){
        /*Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());*/

        Book book = Book.builder()
                        .name(form.getName())
                                .price(form.getPrice())
                                        .stockQuantity(form.getStockQuantity())
                                                .author(form.getAuthor())
                                                        .isbn(form.getIsbn())
                                                                .build();

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model){
        Item item = itemService.finOne(itemId);
        if (item instanceof Book){
            Book book = (Book) item;

            BookForm form = new BookForm();
            form.setId(book.getId());
            form.setName(book.getName());
            form.setPrice(book.getPrice());
            form.setStockQuantity(book.getStockQuantity());
            form.setDtype(book.getDtype());
            form.setIsbn(book.getIsbn());
            form.setAuthor(book.getAuthor());

            model.addAttribute("form",form);
        }
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId,@ModelAttribute BookForm form, Model model){

      itemService.update(form);

        return "redirect:/items";
    }
}
