import createBrowserHistory from 'history/createBrowserHistory';

const history = createBrowserHistory({forceRefresh: true});

export const redirectWithId=(url)=>{
    history.push(url)
}
