function saveTokenToCookie(value: string) {
  document.cookie = `whistle-on_token=${value}`;
}

function saveUserToCookie(value: string) {
  document.cookie = `whistle-on_user=${value}`;
}

function getTokenFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)whistle-on_token\s*=\s*([^;]*).*$)|^.*$/, '$1'
  );
}

function getUserFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)whistle-on_user\s*=\s*([^;]*).*$)|^.*$/, '$1'
  );
}

function deleteCookies(value: string) {
  document.cookie = `${value}=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export {
  saveTokenToCookie,
  saveUserToCookie,
  getTokenFromCookie,
  getUserFromCookie,
  deleteCookies
};
