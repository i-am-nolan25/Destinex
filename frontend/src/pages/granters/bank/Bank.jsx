import Header from '../../../components/header/header-no-background/Header';
import AddBankAcc from './add-bank-account/AddBankAcc';
import Footer from '../../../components/footer/Footer';
import './bank.css'

function BankAcc() {
    return (
        <div className="bank">
            <Header isGrant={true}></Header>
            <AddBankAcc></AddBankAcc>
            <Footer></Footer>
        </div>
    );
}

export default BankAcc;