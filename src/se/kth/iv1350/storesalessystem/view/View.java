package se.kth.iv1350.storesalessystem.view;

import se.kth.iv1350.storesalessystem.controller.Controller;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Represents the user interface of the application.
 */
public class View {
    private final Controller controller;

    /**
     * Creates a new view.
     *
     * @param controller The controller this view uses for operations.
     */
    public View(Controller controller){
        this.controller = controller;
    }

    /**
     * Displays information about an item.
     *
     * @param itemInfo The item to display.
     */
    public void displayItem(ItemDTO itemInfo){
        System.out.println("Item ID: " + itemInfo.getItemID());
        System.out.println("Item name: " + itemInfo.getName());
        System.out.println("Item cost: " + formatAmount(itemInfo.getPrice().getAmount()) + " SEK");
        System.out.println("VAT: " + (int)(itemInfo.getTax() * 100) + "%");
        System.out.println("Item description: " + itemInfo.getDescription());
        System.out.println();
    }

    /**
     * Displays the change to be given to the customer.
     *
     * @param total The change amount.
     */
    public void displayCurrentTotal(Amount total){
        System.out.println("Total cost (incl VAT): " + formatAmount(total.getAmount()) + " SEK");
        System.out.println();
    }

    public void displayTotalVAT() {
        Amount vat = controller.getTotalVAT();
        System.out.println("Total VAT: " + formatAmount(vat.getAmount()) + " SEK");
    }

    /**
     * Displays the change to be given to the customer.
     *
     * @param change The change amount.
     */
    public void displayChange(Amount change){
        System.out.println("Change: " + formatAmount(change.getAmount()) + " SEK");
    }

    private String formatAmount(double amount){
        return String.format("%.2f", amount).replace(".", ":") + " SEK";
    }
}
