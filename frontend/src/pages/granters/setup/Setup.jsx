import Header from '../../../components/header/header-no-background/Header';
import Requirement from './requirement/Requirement';
import SetupPage from './setup-fom/SetupForm';
import Footer from '../../../components/footer/Footer';
import './setup.css'

function Setup() {
    return (
        <div className="setup">
            <Header isGrant={true}></Header>
            <Requirement></Requirement>
            <SetupPage></SetupPage>
            <Footer></Footer>
        </div>
    );
}

export default Setup;