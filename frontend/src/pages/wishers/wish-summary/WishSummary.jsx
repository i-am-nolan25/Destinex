import Header from '../../../components/header/header-no-background/Header';
import Review from './review/Review';
import OrderSummary from './order-summary/OrderSummary';
import Payment from './payment/Payment';
import Footer from '../../../components/footer/Footer';
import './wish_summary.css';

function WishFormSummary() {
    return (
        <div className="wishSummary">
            <Header></Header>
            <Review></Review>
            <OrderSummary></OrderSummary>
            <Payment></Payment>
            <Footer></Footer>
        </div>
    );
}

export default WishFormSummary;