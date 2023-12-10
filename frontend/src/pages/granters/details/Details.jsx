import Header from '../../../components/header/header-no-background/Header';
import Footer from '../../../components/footer/Footer';
import './Details.css'
import DetailsBody from "./details-body/DetailsBody";

function Details() {
    return (
        <div className="waitingPayment">
            <Header></Header>
            <DetailsBody></DetailsBody>
            <Footer></Footer>
        </div>
    );
}

export default Details;