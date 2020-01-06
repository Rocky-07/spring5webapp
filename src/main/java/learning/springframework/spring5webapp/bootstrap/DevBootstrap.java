package learning.springframework.spring5webapp.bootstrap;

import learning.springframework.spring5webapp.model.Author;
import learning.springframework.spring5webapp.model.Book;
import learning.springframework.spring5webapp.model.Publisher;
import learning.springframework.spring5webapp.repositories.AuthorRepository;
import learning.springframework.spring5webapp.repositories.BookRepository;
import learning.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Publisher pub1 = new Publisher("Harper Collins", "A-Block G2 Sonesta iWoods");
        Book b1 = new Book("Domain driven desing", "1234", pub1);
        eric.getBooks().add(b1);
        b1.getAuthors().add(eric);
        publisherRepository.save(pub1);
        authorRepository.save(eric);
        bookRepository.save(b1);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher pub2 = new Publisher("Worx", "Rahul Enclave Flat no-204");
        Book b2 = new Book("J2EE Development without EJB", "2344", pub2);
        rod.getBooks().add(b2);
        publisherRepository.save(pub2);
        authorRepository.save(rod);
        bookRepository.save(b2);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
