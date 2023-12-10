import Header from '../../../components/header/header-no-background/Header';
import WishProduct from './wish-form-product/WishFormProduct';
import Footer from '../../../components/footer/Footer';
import './wish_product.css';
import {useNavigate} from "react-router-dom";

function WishFormProduct() {
    return (
        <div className="wishProduct">
            <Header isGrant={false}></Header>
            <WishProduct></WishProduct>
            <Footer></Footer>
        </div>
    );
}

export default WishFormProduct;