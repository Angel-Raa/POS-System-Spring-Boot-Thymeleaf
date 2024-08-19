$(document).ready(function() {
  $('#customerId').select2({
      placeholder: 'Search for a customer',
      ajax: {
          url: 'http://localhost:9090/pos/api/v1/customers', // URL de la API
          dataType: 'json',
          delay: 250,
          data: function(params) {
              return {
                  query: params.term // término de búsqueda
              };
          },
          processResults: function(data) {
              return {
                  results: data.map(function(customer) {
                      return { id: customer.customerId, text: customer.firstName + ' ' + customer.lastName };
                  })
              };
          },
          cache: true
      },
      minimumInputLength: 1
  });
});
