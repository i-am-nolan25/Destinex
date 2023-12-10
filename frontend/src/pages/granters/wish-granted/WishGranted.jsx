import Header from '../../../components/header/header-no-background/Header';
import Footer from '../../../components/footer/Footer';
import './WishGranted.css'
import WishGrantedBody from "./wish-granted-body/WishGrantedBody";

function WishGranted() {
    return (
        <div className="wishgranted">
            <Header></Header>
            <WishGrantedBody></WishGrantedBody>
            <Footer></Footer>
        </div>
    );
}

export default WishGranted;