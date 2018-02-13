package com.github.michaelruocco.connectfour.model;

import org.junit.Test;

import static java.awt.Color.RED;
import static java.awt.Color.WHITE;
import static java.awt.Color.YELLOW;
import static org.assertj.core.api.Assertions.assertThat;

public class TokenTest {

    private Token redToken = new RedToken();
    private Token yellowToken = new YellowToken();
    private Token emptyToken = new EmptyToken();

    @Test
    public void shouldReturnColor() {
        assertThat(redToken.getColor()).isEqualTo(RED);
        assertThat(yellowToken.getColor()).isEqualTo(YELLOW);
        assertThat(emptyToken.getColor()).isEqualTo(WHITE);
    }

    @Test
    public void toStringShouldReturnTokenValue() {
        assertThat(redToken.toString()).isEqualTo("R");
        assertThat(yellowToken.toString()).isEqualTo("Y");
        assertThat(emptyToken.toString()).isEqualTo("-");
    }

    @Test
    public void equalsShouldReturnTrueForSameToken() {
        assertThat(redToken.equals(new RedToken())).isTrue();
    }

    @Test
    public void equalsShouldReturnFalseForDifferentToken() {
        assertThat(redToken.equals(yellowToken)).isFalse();
    }

    @Test
    public void equalsShouldReturnFalseForDifferentObjectType() {
        assertThat(redToken.equals(new Object())).isFalse();
    }

    @Test
    public void hashCodeShouldReturnSameValueForSameToken() {
        int hash1 = redToken.hashCode();
        int hash2 = new RedToken().hashCode();
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    public void hashCodeShouldReturnDifferentValueForDifferentToken() {
        int hash1 = redToken.hashCode();
        int hash2 = yellowToken.hashCode();
        assertThat(hash1).isNotEqualTo(hash2);
    }

}
