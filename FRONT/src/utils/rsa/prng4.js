/* eslint-disable */

const Arcfour = (function () {
  function Arcfour () {
    this.i = 0
    this.j = 0
    this.S = new Array()
  }

  // Initialize arcfour context from key, an array of ints, each from [0..255]
  function ARC4init (key) {
    var i, j, t
    for (i = 0; i < 256; ++i) { this.S[i] = i }
    j = 0
    for (i = 0; i < 256; ++i) {
      j = (j + this.S[i] + key[i % key.length]) & 255
      t = this.S[i]
      this.S[i] = this.S[j]
      this.S[j] = t
    }
    this.i = 0
    this.j = 0
  }

  function ARC4next () {
    var t
    this.i = (this.i + 1) & 255
    this.j = (this.j + this.S[this.i]) & 255
    t = this.S[this.i]
    this.S[this.i] = this.S[this.j]
    this.S[this.j] = t
    return this.S[(t + this.S[this.i]) & 255]
  }

  Arcfour.prototype.init = ARC4init
  Arcfour.prototype.next = ARC4next
  // Plug in your RNG constructor here
  function prng_newstate () {
    return new Arcfour()
  }

  // Pool size must be a multiple of 4 and greater than 32.
  // An array of bytes the size of the pool will be passed to init()
  // var rng_psize = 256;
  return prng_newstate()
})()

console.log('Arcfour', Arcfour)
if (typeof module === 'object' && module.exports) {
  module.exports = Arcfour
}