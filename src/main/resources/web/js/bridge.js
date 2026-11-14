// Bridge helper for JavaFX WebView communication
const KubePulseBridge = {
    triggerDeployment: function(repoUrl) {
        if (window.javaBridge) {
            window.javaBridge.triggerDeployment(repoUrl);
        } else {
            console.error("JavaBridge is not initialized!");
        }
    },

    runTerraform: function() {
        if (window.javaBridge) {
            window.javaBridge.runTerraformApply();
        } else {
            console.error("JavaBridge is not initialized!");
        }
    },

    runRollback: function() {
        if (window.javaBridge) {
            window.javaBridge.runK8sRollback();
        } else {
            console.error("JavaBridge is not initialized!");
        }
    }
};