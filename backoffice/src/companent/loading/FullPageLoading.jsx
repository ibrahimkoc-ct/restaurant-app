import React, {Component} from 'react';
import LoadingGif from '../../image/loader.gif'

class FullPageLoading extends Component {
    state ={

    }
    render() {
        return (
            <div className="loader-contaier">
                <div className="loader" >
                    <img src={LoadingGif}/>
                </div>

            </div>
        );
    }
}

export default FullPageLoading;