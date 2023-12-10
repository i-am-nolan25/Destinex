import Header from '../../../components/header/header-no-background/Header';
import WishRecipient from './wish-form-recipient/WishFormRecipient';
import Footer from '../../../components/footer/Footer';
import './wish_recipient.css';
import {useNavigate} from "react-router-dom";
import {useAuth} from "../../../networks/hooks/UseAuth";

function WishFormRecipient() {
    const navigate = useNavigate();
    const { isLoggedIn } = useAuth();

    if (!isLoggedIn) {
        navigate("/");
    }

    return (
        <div className="wishRecipient">
            <Header></Header>
            <WishRecipient></WishRecipient>
            <Footer></Footer>
        </div>
    );
}

export default WishFormRecipient;