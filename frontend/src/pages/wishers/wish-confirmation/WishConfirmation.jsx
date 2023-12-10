import Header from '../../../components/header/header-no-background/Header';
import Footer from '../../../components/footer/Footer';
import './WishConfirmation.css'
import WishConfirmationBody from "./wish-confirmation-body/WishConfirmationBody";

function WishConfirmation() {
    return (
        <div className="wishConfirmation">
            <Header></Header>
            <WishConfirmationBody></WishConfirmationBody>
            <Footer></Footer>
        </div>
    );
}

export default WishConfirmation;