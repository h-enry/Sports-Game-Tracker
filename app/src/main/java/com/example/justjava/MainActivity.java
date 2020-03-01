
package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


        /**
         * This method is called when the plus button is clicked.
         */
        public void increment (View view){
            if (quantity == 100) {
                return;
            }
            quantity = quantity + 1;
            displayQuantity(quantity);
        }

        /**
         * This method is called when the minus button is clicked.
         */
        public void decrement(View view){
            if (quantity == 1) {
                return;
            }
            quantity = quantity - 1;
            displayQuantity(quantity);
        }


        /**
         * This method displays the given quantity value on the screen.
         */
        private void displayQuantity(int number){
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("" + number);
        }





        /**
         * Calculates the price of the order.
         *
         *  quantity is the number of cups of coffee ordered
         */
        private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
            // First calculate the price of one cup of coffee
            int basePrice = 5;

            // If the user wants whipped cream, add $1 per cup
            if (addWhippedCream) {
                basePrice = basePrice + 1;
            }

            // If the user wants chocolate, add $2 per cup
            if (addChocolate) {
                basePrice = basePrice + 2;
            }

            // Calculate the total order price by multiplying by the quantity
            return quantity * basePrice;
        }

        private String createOrderSummary (String name,int price, boolean addWhippedCream, boolean addChocolate){
            String priceMessage = "Name: " + name;
            priceMessage += "\nAdd whipped cream?" + addWhippedCream;
            priceMessage += "\nAdd chocolate?" + addChocolate;
            priceMessage += "\nQuantity: " + quantity;
            priceMessage += "\nTotal: $" + price;
            priceMessage += "\nThank you!";
            return priceMessage;
        }
    }