export function decodeToken(token) {
    const _decodeToken = (token) => {
        try {
            return JSON.parse(atob(token));
        } catch {
            return;
        }
    };
    return token
        .split('.')
        .map(token => _decodeToken(token))
        .reduce((acc, curr) => {
            if (!!curr) acc = { ...acc, ...curr };
            return acc;
        }, Object.create(null));
}