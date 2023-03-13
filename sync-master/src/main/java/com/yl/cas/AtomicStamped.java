package com.yl.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStamped {
    public static void main(String[] args) {
    Book javaBook = new Book(1, "javaBook");
    AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<Book>(javaBook, 1);
    System.out.println("\t\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

    Book mysqlBook = new Book(2, "mysqlBook");
    boolean b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
    System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

    boolean c = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
    System.out.println(c + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
}

}
@NoArgsConstructor
@AllArgsConstructor
@Data
class Book {
    private int id;
    private String bookName;
}


