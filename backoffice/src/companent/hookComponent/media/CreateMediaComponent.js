import React, {useState, useEffect} from 'react';
import HeaderComponent from "../../homepage/HeaderComponent";
import FullPageLoading from "../../loading/FullPageLoading";
import {Link} from "react-router-dom";

function MediaListComponent() {
    const [selectedFile, setSelectedFile] = useState();
    const [imageList, setImageList] = useState();
    const [loading, setLoading] = useState(false);
    const onImageChange = event => {
        if (event.target.files && event.target.files[0]) {
            setSelectedFile(event.target.files[0])
        }
    }
    function debugBase64(base64URL) {
        var win = window.open();
        win.document.write('<iframe src="' + base64URL + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
    }

    const onFileUpload = async () => {
        if (!selectedFile) {
            window.alert("File seçili değil!")
            return;
        }
        setLoading(true)
        const data = new FormData();
        data.append("file", selectedFile);
        data.append("imageName", selectedFile.name);
        fetch("http://localhost:8080/file", {
            method: 'POST',
            mode: 'no-cors',
            body: data
        }).then(respose => respose.text(), setLoading(false))
            .then(result => console.warn("result: ", result),
            )
            .catch(error => console.warn("error", error))

    };

    useEffect(() => {
        setLoading(true)
        var requestOptions = {
            method: 'GET'
        };
        fetch("http://localhost:8080/file", requestOptions)
            .then(respose => respose.text(), setLoading(false))
            .then(result => setImageList(JSON.parse(result)),
            )
            .catch(error => console.log('error', error));

    }, [selectedFile]);

    const getFiles = () => {
        if (!imageList) {
            return null;
        }
        let list = [];
        imageList.map(v => {
            list.push(
                <div  key={v.name}>
                    <li>
                        <a href={'data:image/png;base64,' + v.fileContent}
                           onClick={() => debugBase64('data:image/png;base64,' + v.fileContent)}
                           target="_blank">{v.name}
                        </a>
                    </li>
                </div>
            )
        })
        return (
            <ul>
                {list}
            </ul>
        )
    }

    return (
        <div>
            <HeaderComponent/>
            <div className="login">
                <div className="card col-md-4 offset-md-4 offset-md-4  ">
                    <h3 className="text-center kullanicigiris">Resim Ekle</h3>
                    <div className="card-body">

                        <div className="form-group">

                            <input id="uploadFile" type="file" name="file" style={{paddingTop: 20}}
                                   onChange={(e) => onImageChange(e)}/>
                            <Link to='/products'>
                                <button style={{marginTop: 20}} onClick={() => onFileUpload()}>Upload Image</button>
                            </Link>
                            {getFiles()}
                        </div>


                    </div>
                </div>
            </div>
            {loading ? <FullPageLoading/> : null}
        </div>

    );
}


export default MediaListComponent;