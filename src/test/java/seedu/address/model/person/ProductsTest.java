package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ProductsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Products(null));
    }

    @Test
    public void constructor_invalidProducts_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Products("Muffin, @@"));
    }

    @Test
    public void isValidProducts() {
        // null products
        assertThrows(NullPointerException.class, () -> Products.isValidProducts(null));

        // invalid products
        assertFalse(Products.isValidProducts("")); // empty string
        assertFalse(Products.isValidProducts(" ")); // spaces only
        assertFalse(Products.isValidProducts("Muffin, ")); // empty item
        assertFalse(Products.isValidProducts(",Muffin")); // empty item
        assertFalse(Products.isValidProducts("Muffin,,Cookie")); // empty item
        assertFalse(Products.isValidProducts("Muffin, @@@")); // invalid items
        assertFalse(Products.isValidProducts("Tiramisu")); // not in placeholder list
        assertTrue(Products.isValidProducts("Muffin, Chocolate Cake, Vanilla Cake, Brownie, Cookie, Muffin"));
        // duplicates do not count as extra items

        // valid products
        assertTrue(Products.isValidProducts("Chocolate Cake"));
        assertTrue(Products.isValidProducts("Chocolate Cake, Vanilla Cake"));
        assertTrue(Products.isValidProducts("chocolate cake"));
    }

    @Test
    public void constructor_duplicateProducts_deduplicates() {
        Products products = new Products("Muffin, Muffin, Chocolate Cake");

        assertEquals(List.of("Muffin", "Chocolate Cake"), products.getItems());
    }

    @Test
    public void equals() {
        Products products = new Products("Chocolate Cake, Vanilla Cake");

        // same values -> returns true
        assertTrue(products.equals(new Products("Chocolate Cake, Vanilla Cake")));

        // same object -> returns true
        assertTrue(products.equals(products));

        // null -> returns false
        assertFalse(products.equals(null));

        // different types -> returns false
        assertFalse(products.equals(5.0f));

        // different values -> returns false
        assertFalse(products.equals(new Products("Chocolate Cake")));
    }
}
