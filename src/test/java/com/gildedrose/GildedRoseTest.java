package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        //Item[] items = new Item[] { new Item("foo", 0, 0) };
        Item[] items = new Item[]{new Item("fixme",0,0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", items[0].name);
    }
    @Test
    void updateBackstagePassesTest(){
        Item[] items = new Item[]{
            new Item("Backstage passes to a TAFKAL80ETC concert",13,18),
            new Item("Backstage passes to a TAFKAL80ETC concert",9,2),
            new Item("Backstage passes to a TAFKAL80ETC concert",1,47),
            new Item("Backstage passes to a TAFKAL80ETC concert",4,36),
            new Item("Backstage passes to a TAFKAL80ETC concert",0,48)};
        GildedRose app = new GildedRose(items);
        app.updateQualityBis();
        Assertions.assertEquals(app.getItems()[0].sellIn,12);
        Assertions.assertEquals(app.getItems()[0].quality,19);
        Assertions.assertEquals(app.getItems()[1].quality,4);
        Assertions.assertEquals(app.getItems()[2].quality,50);
        Assertions.assertEquals(app.getItems()[3].quality,39);
        Assertions.assertEquals(app.getItems()[4].quality,0);
    }
    @Test
    void updateAgedBrieTest(){
        Item[] items = new Item[]{
            new Item("Aged Brie",-1,49),
            new Item("Aged Brie",10,10)};
        GildedRose app = new GildedRose(items);
        app.updateQualityBis();
        Assertions.assertEquals(app.getItems()[0].sellIn,-2);
        Assertions.assertEquals(app.getItems()[0].quality,50);
        Assertions.assertEquals(app.getItems()[1].quality,11);
    }
    @Test
    void updateSulfurasTest(){
        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros",0,80)};
        GildedRose app = new GildedRose(items);
        app.updateQualityBis();
        Assertions.assertEquals(app.getItems()[0].sellIn,0);
        Assertions.assertEquals(app.getItems()[0].quality,80);
    }
    @Test
    void updateOthersItemsTest(){
        Item[] items = new Item[]{
            new Item("Conjured",2,6),
            new Item("Conjured",0,4),
            new Item("Conjured",-1,2),
            new Item("+5 Dexterity Vest",-2,0)};
        GildedRose app = new GildedRose(items);
        app.updateQualityBis();
        Assertions.assertEquals(app.getItems()[1].sellIn,-1);
        Assertions.assertEquals(app.getItems()[0].quality,5);
        Assertions.assertEquals(app.getItems()[1].quality,2);
        Assertions.assertEquals(app.getItems()[2].quality,0);
        Assertions.assertEquals(app.getItems()[3].quality,0);
    }
}
