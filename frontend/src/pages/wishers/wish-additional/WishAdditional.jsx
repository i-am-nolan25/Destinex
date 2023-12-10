import Header from '../../../components/header/header-no-background/Header';
import WishAdditional from './wish-form-additional/WishFormAdditional';
import Footer from '../../../components/footer/Footer';
import './wish_additional.css';

function WishFormAddtional() {
    return (
        <div className="wishProduct">
            <Header></Header>
            <WishAdditional></WishAdditional>
            <Footer></Footer>
        </div>
    );
}

export default WishFormAddtional;