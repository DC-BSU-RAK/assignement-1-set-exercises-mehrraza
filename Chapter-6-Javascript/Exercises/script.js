// Wait for the DOM to be fully loaded before executing the script
document.addEventListener('DOMContentLoaded', function() {
    // Get references to the DOM elements we need to work with
    const pricePerLiterInput = document.getElementById('price-per-liter');
    const litersInput = document.getElementById('liters');
    const calculateBtn = document.getElementById('calculate-btn');
    const totalCostDisplay = document.getElementById('total-cost');
    
    // Add click event listener to the calculate button
    calculateBtn.addEventListener('click', calculateTotalCost);
    
    // Function to calculate and display the total cost
    function calculateTotalCost() {
        // Get the values from the input fields and convert them to numbers
        const pricePerLiter = parseFloat(pricePerLiterInput.value);
        const liters = parseFloat(litersInput.value);
        
        // Calculate the total cost
        const totalCost = pricePerLiter * liters;
        
        // Display the result with 2 decimal places and £ symbol
        totalCostDisplay.textContent = `Total cost: £${totalCost.toFixed(2)}`;
    }
    
    // Initial calculation when page loads
    calculateTotalCost();
});