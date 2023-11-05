function hideSuccessMessage() {
    var successElement = document.getElementById('success');
    if (successElement) {
        successElement.classList.add('d-none');
    }
}

function hideErrorMessage() {
    var errorElement = document.getElementById('error');
    if (errorElement) {
        errorElement.classList.add('d-none');
    }
}

