import Pages.P01_HomePage;

import Pages.P02_SearchResultPage;
import Pages.P03_ProductPage;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static Util.Utility.*;

public class UI_Test extends TestBase {

    //define test data
    String item;

    {
        try {
            item = getSingleJsonData(returnDataPath("searchForProduct.json"), "item");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    String selectedItem;

    {
        try {
            selectedItem = getSingleJsonData(returnDataPath("searchForProduct.json"), "selectedItem");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    String categoryTab;

    {
        try {
            categoryTab = getSingleJsonData(returnDataPath("searchForProduct.json"), "categoryTap");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    String productName;

    {
        try {
            productName = getSingleJsonData(returnDataPath("searchForProduct.json"), "productName");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    @Test(priority = 1, description = "check that if there is no stock then out of stock will appear for each color")
    public void testcase() {
        new P01_HomePage(driver)
                .searchForProduct(item)
                .selectFromSearchList(selectedItem);
        new P02_SearchResultPage(driver)
                .selectFromCategoryTabSection(categoryTab)
                .selectFromProductList(productName);

        Assert.assertTrue( new P03_ProductPage(driver).checkProductColors());

    }

}

