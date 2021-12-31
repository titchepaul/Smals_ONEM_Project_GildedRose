package com.gildedrose;

class GildedRose {
    private Item[] items;

    private static final String AGED_BRIE_NAME = "Aged Brie";
    private static final String BACKSTAGE_PASSES_NAME = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    private static final int MAX_QUALITY_VALUE = 50;
    private static final int MIN_QUALITY_VALUE = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }


    public Item[] getItems() {
        return items;
    }
    /**
     * this method update all items
     */
    public void updateQualityBis(){

        for(Item item : items){
            if(item.name.equals(SULFURAS_NAME)) {
                continue;
            }
            updateSellIn(item);
            if(item.name.equals(AGED_BRIE_NAME)){
                updateAgedBrie(item);
            }else if(item.name.equals(BACKSTAGE_PASSES_NAME)){
                updateBackstagePasses(item);
            }else{
                updateOthersItems(item);
            }
        }
    }

    /**
     * @param item to update for BackstagePasses
     */
    private void updateBackstagePasses(Item item){
        if(item.sellIn < MIN_QUALITY_VALUE){
            item.quality = MIN_QUALITY_VALUE;
        }else{
            if(item.sellIn > 9 ){
                increaseQuality(item, 1);
            }else if(item.sellIn < 10 && item.sellIn > 4){
                increaseQuality(item, 2);
            }else{
                increaseQuality(item, 3);
            }
        }
    }

    /**
     *
     * @param item to update for AgedBrie
     */
    private void updateAgedBrie(Item item){
        if(item.sellIn >= MIN_QUALITY_VALUE && item.quality <= MAX_QUALITY_VALUE){
            increaseQuality(item, 1);
        }else{
            if(item.sellIn < MIN_QUALITY_VALUE){
                increaseQuality(item, 2);
            }
        }
    }

    /**
     *
     * @param item to update except(agedBrie,backstage and sulfuras)
     */
    private void updateOthersItems(Item item){
        if(item.sellIn >= MIN_QUALITY_VALUE && item.quality > MIN_QUALITY_VALUE){
            decreaseQuality(item, 1);
        }else{
            if(item.sellIn < MIN_QUALITY_VALUE){
                decreaseQuality(item, 2);
            }
        }
    }

    /**
     *
     * @param item decrease the item's sellIn
     */
    private void updateSellIn(Item item){
        item.sellIn = item.sellIn - 1;
    }

    /**
     *
     * @param item to update quality
     * @param increaseValue indicates how much the value of the quality will be increased
     */
    private void increaseQuality(Item item, int increaseValue){
        if((item.quality + increaseValue) > MAX_QUALITY_VALUE){
            item.quality = MAX_QUALITY_VALUE;
        }else{
            item.quality = item.quality + increaseValue;
        }
    }

    /**
     *
     * @param item to update quality
     * @param decreaseValue indicates how much the quality will be decreased
     */
    private void decreaseQuality(Item item, int decreaseValue){
        if((item.quality - decreaseValue) > MIN_QUALITY_VALUE){
            item.quality = item.quality - decreaseValue;
        }else{
            item.quality = MIN_QUALITY_VALUE;
        }
    }
}
