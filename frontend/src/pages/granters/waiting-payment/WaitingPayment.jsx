import Header from '../../../components/header/header-no-background/Header';
import Footer from '../../../components/footer/Footer';
import './WaitingPayment.css'
import WaitingPaymentBody from "./waiting-payment-body/WaitingPaymentBody";

function WaitingPayment() {
    return (
        <div className="waitingPayment">
            <Header></Header>
            <WaitingPaymentBody></WaitingPaymentBody>
            <Footer></Footer>
        </div>
    );
}

export default WaitingPayment;