// UI State Updates aur Event Listeners
console.log("KubePulse Studio UI Loaded Successfully.");

function updateLogBox(message) {
    const logContainer = document.getElementById('logs');
    logContainer.innerText += "\n" + message;
    logContainer.scrollTop = logContainer.scrollHeight; // Auto scroll to bottom
}

// Example function to call Java bridge methods dynamically
function triggerTerraformProvisioning() {
    updateLogBox("[INFO] Initializing Terraform infrastructure scripts...");
    if (window.javaBridge) {
        window.javaBridge.runTerraformApply();
    } else {
        console.warn("Java Bridge not attached!");
    }
}