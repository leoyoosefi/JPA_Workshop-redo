package se.lexicon.jpa_workshopredo.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book
{
    @Id // primary key for id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int maxLoanDays;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_author"
            ,joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")}
            ,inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private Set<Author> authors = new HashSet<>();

    public Book()
    {
    }

    public Book(String isbn, String title, int maxLoanDays)
    {
        this();
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getMaxLoanDays()
    {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays)
    {
        this.maxLoanDays = maxLoanDays;
    }

    public Set<Author> getAuthors()
    {
        return authors;
    }

    public void setAuthors(Set<Author> authors)
    {
        this.authors = authors;
    }

    public boolean equalsForAuthor(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() && getMaxLoanDays() == book.getMaxLoanDays() && getIsbn().equals(book.getIsbn()) && getTitle().equals(book.getTitle());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;

        boolean isEquals = getId() == book.getId() && getMaxLoanDays() == book.getMaxLoanDays() && getIsbn().equals(book.getIsbn()) && getTitle().equals(book.getTitle());

        Iterator<Author> it = getAuthors().iterator();
        Iterator<Author> il = book.getAuthors().iterator();

        isEquals = isEquals && getAuthors().size() == book.getAuthors().size();

        while(isEquals && it.hasNext() && il.hasNext())
            isEquals = it.next().equalsForAuthor(il.next());

        return isEquals;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getIsbn(), getTitle(), getMaxLoanDays());
    }

    public String toStringForAuthor()
    {
        return "Book{" +
                "id=" + getId() +
                ", isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", maxLoanDays=" + getMaxLoanDays() +
                '}';
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder("Book{" +
                "id=" + getId() +
                ", isbn='" + getIsbn() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", maxLoanDays=" + getMaxLoanDays() +
                '}');

        int count = 0;
        for(Author author : authors)
        {
            count++;
            if(count < authors.size())
            {
                str.append(author.toStringForBook()).append('\'').append(", ");
            }
            else
            {
                str.append(author.toStringForBook()).append('}');
            }
        }

        return str.toString();
    }
}
