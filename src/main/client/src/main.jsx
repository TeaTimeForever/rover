import React from 'react';
import ReactDOM from 'react-dom';
import Loans from 'loansList.jsx'

main();

function main() {
    ReactDOM.render(<Loans/>, document.getElementById('app'));
}